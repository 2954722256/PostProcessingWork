package com.nvidia.developer.opengl.models.sdkmesh;

import jet.opengl.postprocessing.util.Numeric;

final class SDKMeshIndexBufferHeader {

	static final int SIZE = 32;
	
	long numVertices;  // 8
	long sizeBytes;    // 16
	int indexType;     // 20
					   // 24  pad
	long dataOffset;   // 32 (This also forces the union to 64bits)
	int buffer;
	
	int load(byte[] data, int offset){
		numVertices = Numeric.getLong(data, offset); offset += 8;
		sizeBytes = Numeric.getLong(data, offset); offset += 8;
		indexType = Numeric.getInt(data, offset); offset += 8;  // TODO
		dataOffset = Numeric.getLong(data, offset); offset += 8;
		buffer = (int)dataOffset;
		return offset;
	}

	@Override
	public String toString() {
		return "SDKMeshIndexBufferHeader [numVertices=" + numVertices + ", sizeBytes=" + sizeBytes + ", indexType="
				+ indexType + ",\n dataOffset=" + dataOffset + ", buffer=" + buffer + "]";
	}
	
}