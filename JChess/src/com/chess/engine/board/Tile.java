package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {

	protected final int tileCoordinate;

	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

	Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}

	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTilesMap = new HashMap<>();
		for (int i = 0; i < 64; i++) {
			emptyTilesMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTilesMap);
	}

	public static Tile createTile(final int tileCoordinate, Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	public abstract boolean isTileOccupied();

	public abstract Piece getPiece();

	private static final class EmptyTile extends Tile {
		EmptyTile(final int coordinate) {
			super(coordinate);
		}

		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public Piece getPiece() {
			return null;
		}

	}

	private static final class OccupiedTile extends Tile {
		private final Piece pieceOnTile;

		OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}

		@Override
		public boolean isTileOccupied() {
			return true;
		}

		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}

}
