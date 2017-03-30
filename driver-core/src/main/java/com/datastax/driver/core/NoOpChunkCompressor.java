/*
 *      Copyright (C) 2012-2015 DataStax Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.datastax.driver.core;

import java.io.IOException;

/**
 * A "no-op" implementation of {@link ChunkCompressor}.
 * This implementation simply returns the input provided to it. This is used for the case where we want
 * to apply the checksum and chunking logic to an input ByteBuf but don't actually want to compress
 * the bytes.
 */
class NoOpChunkCompressor implements ChunkCompressor {
    @Override
    public int maxCompressedLength(int length) {
        return length;
    }

    @Override
    public int compressChunk(byte[] src, int srcOffset, int length, byte[] dest, int destOffset) throws IOException {
        System.arraycopy(src, srcOffset, dest, destOffset, length);
        return length;
    }

    @Override
    public byte[] decompressChunk(byte[] src, int expectedDecompressedLength) throws IOException {
        return src;
    }
}