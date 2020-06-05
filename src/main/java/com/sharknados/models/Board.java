package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.tiles.*;
import com.sharknados.util.Point;

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
		this.tilePositions = new Tile[2 * size + 1][2 * size + 1];
		List<Tile> battleFieldTiles  = new ArrayList<Tile>();

		// Generate a board. Add tiles to the 2d array
		for (int x = 0; x <= 2 * size; x++) {
			int zStart = max(0, size - x);
			int zStop = min(2 * size, 3 * size - x);
			for (int z = zStart; z <= zStop; z++) {

				Tile tile = new HexagonTile(x, z);
				if (z != 6 && z != 0 && !(x == 2 && z == 5) && !(x == 4 && z == 1))
					battleFieldTiles .add(tile);

				tilePositions[x][z] = tile;
			}
		}

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
	}

	public void setPowerUpsandTraps(List<Point> battleFieldTiles ) {
		List<Point> emptyList = battleFieldTiles ;
		Random randNum = new Random();
		Set<Integer> set = new LinkedHashSet<Integer>();
		int numberOfDecorators=6;
		if(this.size!=3) {
			numberOfDecorators=8;
		}
		while (set.size() < numberOfDecorators) {
			set.add(randNum.nextInt(battleFieldTiles .size() - 1));
		}
		Tile tile;
		int count = 0;
		for (Iterator<Integer> it = set.iterator(); it.hasNext(); count++) {
			int i = it.next();
			Point point = battleFieldTiles .get(i);
			tile = this.getTileAtPosition(point.x(), point.z());
//			System.out.println(it.next()+" "+count);
			if (count < numberOfDecorators/2)
				tilePositions[tile.getX()][tile.getZ()] = new PowerUpTileDecorator(tile);
			else
				tilePositions[tile.getX()][tile.getZ()] = new TrapTileDecorator(tile);

		}

	}

	public void setPassages(List<Point> battleFieldTiles ) {
		Random randNum = new Random();
		Set<Integer> set = new LinkedHashSet<Integer>();
		int numberOfDecorators=4;
		if(this.size!=3) {
			numberOfDecorators=6;
		}
		while (set.size() < numberOfDecorators) {
			set.add(randNum.nextInt(battleFieldTiles .size() - 1));
		}
		Tile tile1;
		Tile tile2;
		for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
			int i = it.next();
			Point point = battleFieldTiles .get(i);
			tile1 = this.getTileAtPosition(point.x(), point.z());
			tile1 = new PassageTileDecorator(tile1);
			i = it.next();
			point = battleFieldTiles .get(i);
			tile2 = this.getTileAtPosition(point.x(), point.z());
			tile2 = new PassageTileDecorator(tile2);
			tile1.setPassageTile(tile2);
			tile2.setPassageTile(tile1);
//			System.out.println(it.next());
			tilePositions[tile1.getX()][tile1.getZ()] = tile1;
			tilePositions[tile2.getX()][tile2.getZ()] = tile2;

			Tile tile = tilePositions[tile1.getX()][tile1.getZ()].getPassageTile();

			System.out.println(tile.getX() + " " + tile.getZ());
			tile = tilePositions[tile2.getX()][tile2.getZ()].getPassageTile();
			System.out.println(tile.getX() + " " + tile.getZ());

		}

	}

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
