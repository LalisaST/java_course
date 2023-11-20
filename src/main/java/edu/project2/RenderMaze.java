package edu.project2;

import java.util.List;

public class RenderMaze implements Renderer {
    private static final String PASSAGE = "   ";
    private static final String WALL = "███";

    @Override
    public String render(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getGrid()[i][j].type() == Cell.Type.PASSAGE) {
                    stringBuilder.append(PASSAGE);
                } else {
                    stringBuilder.append(WALL);
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Point> path) {
        if (maze == null || path == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(new Point(i, j))) {
                    stringBuilder.append(" ● ");
                } else if (maze.getGrid()[i][j].type() == Cell.Type.PASSAGE) {
                    stringBuilder.append(PASSAGE);
                } else {
                    stringBuilder.append(WALL);
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
