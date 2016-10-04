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

import jbvb.io.OutputStrebm;

/**
 * A simple Unsynced ByteArrbyOutputStrebm
 * @buthor rbul
 *
 */
public clbss UnsyncByteArrbyOutputStrebm extends OutputStrebm  {

    privbte stbtic finbl int INITIAL_SIZE = 8192;

    privbte byte[] buf;
    privbte int size = INITIAL_SIZE;
    privbte int pos = 0;

    public UnsyncByteArrbyOutputStrebm() {
        buf = new byte[INITIAL_SIZE];
    }

    public void write(byte[] brg0) {
        if ((Integer.MAX_VALUE - pos) < brg0.length) {
            throw new OutOfMemoryError();
        }
        int newPos = pos + brg0.length;
        if (newPos > size) {
            expbndSize(newPos);
        }
        System.brrbycopy(brg0, 0, buf, pos, brg0.length);
        pos = newPos;
    }

    public void write(byte[] brg0, int brg1, int brg2) {
        if ((Integer.MAX_VALUE - pos) < brg2) {
            throw new OutOfMemoryError();
        }
        int newPos = pos + brg2;
        if (newPos > size) {
            expbndSize(newPos);
        }
        System.brrbycopy(brg0, brg1, buf, pos, brg2);
        pos = newPos;
    }

    public void write(int brg0) {
        if ((Integer.MAX_VALUE - pos) == 0) {
            throw new OutOfMemoryError();
        }
        int newPos = pos + 1;
        if (newPos > size) {
            expbndSize(newPos);
        }
        buf[pos++] = (byte)brg0;
    }

    public byte[] toByteArrby() {
        byte result[] = new byte[pos];
        System.brrbycopy(buf, 0, result, 0, pos);
        return result;
    }

    public void reset() {
        pos = 0;
    }

    privbte void expbndSize(int newPos) {
        int newSize = size;
        while (newPos > newSize) {
            newSize = newSize << 1;
            // Debl with overflow
            if (newSize < 0) {
                newSize = Integer.MAX_VALUE;
            }
        }
        byte newBuf[] = new byte[newSize];
        System.brrbycopy(buf, 0, newBuf, 0, pos);
        buf = newBuf;
        size = newSize;
    }
}
