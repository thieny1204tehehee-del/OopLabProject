package map;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import game.GameConfig;

public class Map {

    private int[][] grid;
    private int tileSize;
    private List<Wall> walls;
    private List<Food> foods;
    

    public Map(int[][] grid){
        this.grid = grid;
        this.tileSize = GameConfig.TILE_SIZE;
        this.walls = new ArrayList<>();
        this.foods = new ArrayList<>();
        loadMap();

    }


    // doc map trong game
    public void loadMap(){
        walls.clear();
        foods.clear();

        //duyệt qua từng ô trong map
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){

                int value = grid[row][col];

                int x = col * tileSize;
                int y = row * tileSize;

                if(value == 1){
                walls.add(new Wall(x, y, tileSize));
                }
                else if(value == 0){
                    foods.add(new Food(x, y, tileSize));
                }
            }
        }
    }

    public void eatFood( Entity e){

    }


}