package data_structures;

/**
 * http://www.sanfoundry.com/java-program-implement-interval-tree/
 */

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class IntervalTree<Type> {

    private IntervalNode<Type> head;
    private List<Interval<Type>> intervalList;
    private boolean inSync;
    private int size;

    public IntervalTree() {
        this.head = new IntervalNode<>();
        this.intervalList = new ArrayList<>();
        this.inSync = true;
        this.size = 0;
    }

    public IntervalTree(List<Interval<Type>> intervalList) {
        this.head = new IntervalNode<>(intervalList);
        this.intervalList = new ArrayList<>();
        this.intervalList.addAll(intervalList);
        this.inSync = true;
        this.size = intervalList.size();
    }

    public List<Type> get(long time) {
        List<Interval<Type>> intervals = getIntervals(time);
        return intervals.stream().map(Interval::getData).collect(Collectors.toList());
    }

    public List<Interval<Type>> getIntervals(long time) {
        build();
        return head.stab(time);
    }

    public List<Type> get(long start, long end) {
        List<Interval<Type>> intervals = getIntervals(start, end);
        return intervals.stream().map(Interval::getData).collect(Collectors.toList());
    }

    public List<Interval<Type>> getIntervals(long start, long end) {
        build();
        return head.query(new Interval<Type>(start, end, null));
    }

    public void addInterval(Interval<Type> interval) {
        intervalList.add(interval);
        inSync = false;
    }

    public void addInterval(long begin, long end, Type data) {
        intervalList.add(new Interval<>(begin, end, data));
        inSync = false;
    }

    public boolean inSync() {
        return inSync;
    }

    public void build() {
        if (!inSync) {
            head = new IntervalNode<>(intervalList);
            inSync = true;
            size = intervalList.size();
        }
    }

    public int currentSize() {
        return size;
    }

    public int listSize() {
        return intervalList.size();
    }

    @Override
    public String toString() {
        return nodeString(head, 0);
    }

    private String nodeString(IntervalNode<Type> node, int level) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("\t");
        }
        sb
                .append(node).append("\n")
                .append(nodeString(node.getLeft(), level + 1))
                .append(nodeString(node.getRight(), level + 1));

        return sb.toString();
    }

    private static class Interval<Type> implements Comparable<Interval<Type>> {

        private long start;
        private long end;
        private Type data;

        public Interval(long start, long end, Type data) {
            this.start = start;
            this.end = end;
            this.data = data;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public Type getData() {
            return data;
        }

        public void setData(Type data) {
            this.data = data;
        }

        public boolean contains(long time) {
            return time < end && time > start;
        }

        public boolean intersects(Interval<?> other) {
            return other.getEnd() > start && other.getStart() < end;
        }

        public int compareTo(Interval<Type> other) {
            if (start < other.getStart()) {
                return -1;
            } else if (start > other.getStart()) {
                return 1;
            } else if (end < other.getEnd()) {
                return -1;
            } else if (end > other.getEnd())
                return 1;
            else {
                return 0;
            }
        }

    }

    private static class IntervalNode<Type> {

        private SortedMap<Interval<Type>, List<Interval<Type>>> intervals;
        private long center;
        private IntervalNode<Type> leftNode;
        private IntervalNode<Type> rightNode;

        public IntervalNode() {
            intervals = new TreeMap<>();
            center = 0;
            leftNode = null;
            rightNode = null;
        }

        public IntervalNode(List<Interval<Type>> intervalList) {

            intervals = new TreeMap<>();

            SortedSet<Long> endpoints = new TreeSet<>();

            for (Interval<Type> interval : intervalList) {
                endpoints.add(interval.getStart());
                endpoints.add(interval.getEnd());
            }

            long median = getMedian(endpoints);
            center = median;

            List<Interval<Type>> left = new ArrayList<>();
            List<Interval<Type>> right = new ArrayList<>();

            for (Interval<Type> interval : intervalList) {
                if (interval.getEnd() < median) {
                    left.add(interval);
                } else if (interval.getStart() > median) {
                    right.add(interval);
                } else {
                    List<Interval<Type>> posting = intervals.get(interval);
                    if (posting == null) {
                        posting = new ArrayList<>();
                        intervals.put(interval, posting);
                    }
                    posting.add(interval);
                }
            }

            if (left.size() > 0) {
                leftNode = new IntervalNode<>(left);
            }
            if (right.size() > 0) {
                rightNode = new IntervalNode<>(right);
            }
        }

        public List<Interval<Type>> stab(long time) {
            List<Interval<Type>> result = new ArrayList<>();

            for (Entry<Interval<Type>, List<Interval<Type>>> entry : intervals.entrySet()) {
                if (entry.getKey().contains(time)) {
                    result.addAll(entry.getValue().stream().collect(Collectors.toList()));
                } else if (entry.getKey().getStart() > time) {
                    break;
                }
            }

            if (time < center && leftNode != null) {
                result.addAll(leftNode.stab(time));
            } else if (time > center && rightNode != null) {
                result.addAll(rightNode.stab(time));
            }
            return result;
        }

        public List<Interval<Type>> query(Interval<?> target) {
            List<Interval<Type>> result = new ArrayList<>();

            for (Entry<Interval<Type>, List<Interval<Type>>> entry : intervals.entrySet()) {
                if (entry.getKey().intersects(target)) {
                    result.addAll(entry.getValue().stream().collect(Collectors.toList()));
                } else if (entry.getKey().getStart() > target.getEnd()) {
                    break;
                }
            }

            if (target.getStart() < center && leftNode != null) {
                result.addAll(leftNode.query(target));
            }
            if (target.getEnd() > center && rightNode != null) {
                result.addAll(rightNode.query(target));
            }

            return result;
        }

        public long getCenter() {
            return center;
        }

        public void setCenter(long center) {
            this.center = center;
        }

        public IntervalNode<Type> getLeft() {
            return leftNode;
        }

        public void setLeft(IntervalNode<Type> left) {
            this.leftNode = left;
        }

        public IntervalNode<Type> getRight() {
            return rightNode;
        }

        public void setRight(IntervalNode<Type> right) {
            this.rightNode = right;
        }

        private Long getMedian(SortedSet<Long> set) {
            int i = 0;
            int middle = set.size() / 2;
            for (Long point : set) {
                if (i == middle)
                    return point;
                i++;
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(center).append(": ");
            for (Entry<Interval<Type>, List<Interval<Type>>> entry : intervals
                    .entrySet()) {
                sb.append("[").append(entry.getKey().getStart()).append(",").append(entry.getKey().getEnd()).append("]:{");
                for (Interval<Type> interval : entry.getValue()) {
                    sb.append("(").append(interval.getStart()).append(",").append(interval.getEnd()).append(",").append(interval.getData()).append(")");
                }
                sb.append("} ");
            }
            return sb.toString();
        }

    }
}
