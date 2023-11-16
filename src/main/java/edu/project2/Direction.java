package edu.project2;

public enum Direction {
    N {
        @Override
        public int[] getMove() {
            return new int[] {-1, 0};
        }
    },
    S {
        @Override
        public int[] getMove() {
            return new int[] {1, 0};
        }
    },

    E {
        @Override
        public int[] getMove() {
            return new int[] {0, 1};
        }
    },

    W {
        @Override
        public int[] getMove() {
            return new int[] {0, -1};
        }
    };

    public abstract int[] getMove();
}
