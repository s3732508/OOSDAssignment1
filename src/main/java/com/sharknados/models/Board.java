package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.*;

public class Board implements java.io.Serializable {

	// Vectors for direction
	public final static int NORTH = 0;
	public final static int NORTHEAST = 1;
	public final static int SOUTHEAST = 2;
	public final static int SOUTH = 3;
	public final static int SOUTHWEST = 4;
	public final static int NORTHWEST = 5;
	public final static int deltaX[] = { 0, 1, 1, 0, -1, -1 };
	public final static int deltaZ[] = { -1, -1, 0, 1, 1, 0 };

	private int size;

	private Tile[][] tilePositions;
	private List<Piece> pieces;

	public Board(int size) {
		this.size = size;
//        this.tilePositions = new Tile[2*size+1][2*size+1];
		this.tilePositions = new Tile[2 * size + 1][2 * size + 1];
		List<Tile> emptytileList = new ArrayList<Tile>();

		// Generate a board. Add tiles to the 2d array
		for (int x = 0; x <= 2 * size; x++) {
			int zStart = max(0, size - x);
			int zStop = min(2 * size, 3 * size - x);
			for (int z = zStart; z <= zStop; z++) {
//                tilePositions[x][z] = new HexagonTile(x,z);
				Tile tile = new HexagonTile(x, z);
				if (z != 6 && z != 0 && !(x == 2 && z == 5) && !(x == 4 && z == 1))
					emptytileList.add(tile);
//            	if(x==2 && z==5) {
//            		
//            		tile=new PassageTileDecorator(new ObstacleTileDecorator(tile));
//            		
////                    tile=new ObstacleTileDecorator(tile);
//            	}

				tilePositions[x][z] = tile;
			}
		}

		
		emptytileList=setPowerUpsandTraps(emptytileList);
		//setPassages(emptytileList);
		

		// For each tile in the board set each of it's neighbors
		for (int x = 0; x <= 2 * size; x++) {
			int zStart = max(0, size - x);
			int zStop = min(2 * size, 3 * size - x);
			for (int z = zStart; z <= zStop; z++) {
				// Loop through each direction setting a neighbor if it exists
				for (int direction = 0; direction < 6; direction++) {
					trySetNeighbor(tilePositions[x][z], direction);
				}
			}
		}

		tilePositions[3][3] = new PowerUpTileDecorator(new PowerUpTileDecorator(new HexagonTile(3,3)));
	}

	private List<Tile> setPowerUpsandTraps(List<Tile> emptytileList) {
		List<Tile> emptyList=emptytileList;
		Random randNum = new Random();
		Set<Integer> set = new LinkedHashSet<Integer>();
		while (set.size() < 6) {
			set.add(randNum.nextInt(emptytileList.size() - 1));
		}
		Tile tile;
		int count = 0;
		for (Iterator<Integer> it = set.iterator(); it.hasNext(); count++) {
			int i = it.next();
			tile = emptytileList.get(i);
//			System.out.println(it.next()+" "+count);
			if (count < 3)
				tilePositions[tile.getX()][tile.getZ()] = new PowerUpTileDecorator(tile);
			else
				tilePositions[tile.getX()][tile.getZ()] = new TrapTileDecorator(tile);
			emptyList.set(i, tilePositions[tile.getX()][tile.getZ()]);
		}
		return emptyList;

	}



/*	private void setPassages(List<Tile> emptytileList) {
		Random randNum = new Random();
		Set<Integer> set = new LinkedHashSet<Integer>();
		while (set.size() < 4) {
			set.add(randNum.nextInt(emptytileList.size() - 1));
		}
		Tile tile1;
		Tile tile2;
		for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
			tile1 = new PassageTileDecorator(emptytileList.get(it.next()));
			tile2 = new PassageTileDecorator(emptytileList.get(it.next()));
			tile1.setPassageTile(tile2);
			tile2.setPassageTile(tile1);
//			System.out.println(it.next());
			tilePositions[tile1.getX()][tile1.getZ()] = tile1;
			tilePositions[tile2.getX()][tile2.getZ()] = tile2;
			
			
			Tile tile=tilePositions[tile1.getX()][tile1.getZ()].getPassageTile();
			
			
			System.out.println(tile.getX()+" "+tile.getZ());
			tile=tilePositions[tile2.getX()][tile2.getZ()].getPassageTile();
			System.out.println(tile.getX()+" "+tile.getZ());
			
			
		}

	}*/

	private boolean trySetNeighbor(Tile tile, int direction) {
		boolean success = false;
		Tile neighbor = null;
		try {
			neighbor = tilePositions[tile.getX() + deltaX[direction]][tile.getZ() + deltaZ[direction]];
		} catch (ArrayIndexOutOfBoundsException e) {
			//
		}
		if (neighbor != null) {
			tile.setNeighbor(neighbor, direction);
			success = true;
		}
		return success;
	}

	public int getSize() {
		return size;
	}

	public Tile[][] getAllTiles() {
		return tilePositions;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public Tile getTileAtPosition(int x, int z) {

		return tilePositions[x][z];
	}

	public Tile getCentreTile() {
		return tilePositions[size][size];
	}

	public List<Tile> getTileList() {
		List<Tile> tileList = new ArrayList<>();
		for (int x = 0; x <= 2 * size; x++) {
			int zStart = max(0, size - x);
			int zStop = min(2 * size, 3 * size - x);
			for (int z = zStart; z <= zStop; z++) {
				tileList.add(tilePositions[x][z]);
			}
		}
		return tileList;
	}

	public int getDistanceBetweenTiles(Tile a, Tile b) {
		// convert to cube coords
		int aX = a.getX() - size;
		int aZ = a.getZ() - size;
		int aY = -aX - aZ;
		int bX = b.getX() - size;
		int bZ = b.getZ() - size;
		int bY = -bX - bZ;

		int dX = abs(aX - bX);
		int dY = abs(aY - bY);
		int dZ = abs(aZ - bZ);

		return max(max(dX, dY), dZ);
	}



}
