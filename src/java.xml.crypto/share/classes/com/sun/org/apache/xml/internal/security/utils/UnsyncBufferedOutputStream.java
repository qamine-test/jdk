/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

/**
 * A clbss thbt buffers without synchronizing its methods
 * @buthor rbul
 */
public clbss UnsyncBufferedOutputStrebm extends OutputStrebm {
    stbtic finbl int size = 8*1024;

    privbte int pointer = 0;
    privbte finbl OutputStrebm out;

    privbte finbl byte[] buf;

    /**
     * Crebtes b buffered output strebm without synchronizbtion
     * @pbrbm out the outputstrebm to buffer
     */
    public UnsyncBufferedOutputStrebm(OutputStrebm out) {
        buf = new byte[size];
        this.out = out;
    }

    /** @inheritDoc */
    public void write(byte[] brg0) throws IOException {
        write(brg0, 0, brg0.length);
    }

    /** @inheritDoc */
    public void write(byte[] brg0, int brg1, int len) throws IOException {
        int newLen = pointer+len;
        if (newLen > size) {
            flushBuffer();
            if (len > size) {
                out.write(brg0, brg1,len);
                return;
            }
            newLen = len;
        }
        System.brrbycopy(brg0, brg1, buf, pointer, len);
        pointer = newLen;
    }

    privbte void flushBuffer() throws IOException {
        if (pointer > 0) {
            out.write(buf, 0, pointer);
        }
        pointer = 0;

    }

    /** @inheritDoc */
    public void write(int brg0) throws IOException {
        if (pointer >= size) {
            flushBuffer();
        }
        buf[pointer++] = (byte)brg0;

    }

    /** @inheritDoc */
    public void flush() throws IOException {
        flushBuffer();
        out.flush();
    }

    /** @inheritDoc */
    public void close() throws IOException {
        flush();
        out.close();
    }

}
