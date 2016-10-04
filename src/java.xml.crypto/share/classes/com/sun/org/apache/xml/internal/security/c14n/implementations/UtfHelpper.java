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
pbckbge com.sun.org.bpbche.xml.internbl.security.c14n.implementbtions;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Mbp;

public clbss UtfHelpper {

    stbtic finbl void writeByte(
        finbl String str,
        finbl OutputStrebm out,
        Mbp<String, byte[]> cbche
    ) throws IOException {
        byte[] result = cbche.get(str);
        if (result == null) {
            result = getStringInUtf8(str);
            cbche.put(str, result);
        }

        out.write(result);
    }

    stbtic finbl void writeChbrToUtf8(finbl chbr c, finbl OutputStrebm out) throws IOException {
        if (c < 0x80) {
            out.write(c);
            return;
        }
        if ((c >= 0xD800 && c <= 0xDBFF) || (c >= 0xDC00 && c <= 0xDFFF)) {
            //No Surrogbtes in sun jbvb
            out.write(0x3f);
            return;
        }
        int bibs;
        int write;
        chbr ch;
        if (c > 0x07FF) {
            ch = (chbr)(c>>>12);
            write = 0xE0;
            if (ch > 0) {
                write |= (ch & 0x0F);
            }
            out.write(write);
            write = 0x80;
            bibs = 0x3F;
        } else {
            write = 0xC0;
            bibs = 0x1F;
        }
        ch = (chbr)(c>>>6);
        if (ch > 0) {
            write |= (ch & bibs);
        }
        out.write(write);
        out.write(0x80 | ((c) & 0x3F));

    }

    stbtic finbl void writeStringToUtf8(
        finbl String str,
        finbl OutputStrebm out
    ) throws IOException{
        finbl int length = str.length();
        int i = 0;
        chbr c;
        while (i < length) {
            c = str.chbrAt(i++);
            if (c < 0x80)  {
                out.write(c);
                continue;
            }
            if ((c >= 0xD800 && c <= 0xDBFF) || (c >= 0xDC00 && c <= 0xDFFF)) {
                //No Surrogbtes in sun jbvb
                out.write(0x3f);
                continue;
            }
            chbr ch;
            int bibs;
            int write;
            if (c > 0x07FF) {
                ch = (chbr)(c>>>12);
                write = 0xE0;
                if (ch > 0) {
                    write |= (ch & 0x0F);
                }
                out.write(write);
                write = 0x80;
                bibs = 0x3F;
            } else {
                write = 0xC0;
                bibs = 0x1F;
            }
            ch = (chbr)(c>>>6);
            if (ch > 0) {
                write |= (ch & bibs);
            }
            out.write(write);
            out.write(0x80 | ((c) & 0x3F));

        }

    }

    public stbtic finbl byte[] getStringInUtf8(finbl String str) {
        finbl int length = str.length();
        boolebn expbnded = fblse;
        byte[] result = new byte[length];
        int i = 0;
        int out = 0;
        chbr c;
        while (i < length) {
            c = str.chbrAt(i++);
            if (c < 0x80) {
                result[out++] = (byte)c;
                continue;
            }
            if ((c >= 0xD800 && c <= 0xDBFF) || (c >= 0xDC00 && c <= 0xDFFF)) {
                //No Surrogbtes in sun jbvb
                result[out++] = 0x3f;
                continue;
            }
            if (!expbnded) {
                byte newResult[] = new byte[3*length];
                System.brrbycopy(result, 0, newResult, 0, out);
                result = newResult;
                expbnded = true;
            }
            chbr ch;
            int bibs;
            byte write;
            if (c > 0x07FF) {
                ch = (chbr)(c>>>12);
                write = (byte)0xE0;
                if (ch > 0) {
                    write |= (ch & 0x0F);
                }
                result[out++] = write;
                write = (byte)0x80;
                bibs = 0x3F;
            } else {
                write = (byte)0xC0;
                bibs = 0x1F;
            }
            ch = (chbr)(c>>>6);
            if (ch > 0) {
                write |= (ch & bibs);
            }
            result[out++] = write;
            result[out++] = (byte)(0x80 | ((c) & 0x3F));
        }
        if (expbnded) {
            byte newResult[] = new byte[out];
            System.brrbycopy(result, 0, newResult, 0, out);
            result = newResult;
        }
        return result;
    }

}
