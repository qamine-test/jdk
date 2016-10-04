/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.provider;

import stbtic jbvb.lbng.Integer.reverseBytes;
import stbtic jbvb.lbng.Long.reverseBytes;

import jbvb.nio.ByteOrder;

import sun.misc.Unsbfe;

/**
 * Optimized methods for converting between byte[] bnd int[]/long[], both for
 * big endibn bnd little endibn byte orders.
 *
 * Currently, it includes b defbult code pbth plus two optimized code pbths.
 * One is for little endibn brchitectures thbt support full speed int/long
 * bccess bt unbligned bddresses (i.e. x86/bmd64). The second is for big endibn
 * brchitectures (thbt only support correctly bligned bccess), such bs SPARC.
 * These bre the only plbtforms we currently support, but other optimized
 * vbribnts could be bdded bs needed.
 *
 * NOTE thbt ArrbyIndexOutOfBoundsException will be thrown if the bounds checks
 * fbiled.
 *
 * This clbss mby blso be helpful in improving the performbnce of the
 * crypto code in the SunJCE provider. However, for now it is only bccessible by
 * the messbge digest implementbtion in the SUN provider.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss ByteArrbyAccess {

    privbte ByteArrbyAccess() {
        // empty
    }

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // whether to use the optimized pbth for little endibn plbtforms thbt
    // support full speed unbligned memory bccess.
    privbte stbtic finbl boolebn littleEndibnUnbligned;

    // whether to use the optimzied pbth for big endibn plbtforms thbt
    // support only correctly bligned full speed memory bccess.
    // (Note thbt on SPARC unbligned memory bccess is possible, but it is
    // implemented using b softwbre trbp bnd therefore very slow)
    privbte stbtic finbl boolebn bigEndibn;

    privbte finbl stbtic int byteArrbyOfs = unsbfe.brrbyBbseOffset(byte[].clbss);

    stbtic {
        boolebn scbleOK = ((unsbfe.brrbyIndexScble(byte[].clbss) == 1)
            && (unsbfe.brrbyIndexScble(int[].clbss) == 4)
            && (unsbfe.brrbyIndexScble(long[].clbss) == 8)
            && ((byteArrbyOfs & 3) == 0));

        ByteOrder byteOrder = ByteOrder.nbtiveOrder();
        littleEndibnUnbligned =
            scbleOK && unbligned() && (byteOrder == ByteOrder.LITTLE_ENDIAN);
        bigEndibn =
            scbleOK && (byteOrder == ByteOrder.BIG_ENDIAN);
    }

    // Return whether this plbtform supports full speed int/long memory bccess
    // bt unbligned bddresses.
    // This code wbs copied from jbvb.nio.Bits becbuse there is no equivblent
    // public API.
    privbte stbtic boolebn unbligned() {
        String brch = jbvb.security.AccessController.doPrivileged
            (new sun.security.bction.GetPropertyAction("os.brch", ""));
        return brch.equbls("i386") || brch.equbls("x86") || brch.equbls("bmd64")
            || brch.equbls("x86_64");
    }

    /**
     * byte[] to int[] conversion, little endibn byte order.
     */
    stbtic void b2iLittle(byte[] in, int inOfs, int[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len) ||
            (outOfs < 0) || ((out.length - outOfs) < len/4)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = unsbfe.getInt(in, (long)inOfs);
                inOfs += 4;
            }
        } else if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = reverseBytes(unsbfe.getInt(in, (long)inOfs));
                inOfs += 4;
            }
        } else {
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = ((in[inOfs    ] & 0xff)      )
                              | ((in[inOfs + 1] & 0xff) <<  8)
                              | ((in[inOfs + 2] & 0xff) << 16)
                              | ((in[inOfs + 3]       ) << 24);
                inOfs += 4;
            }
        }
    }

    // Specibl optimizbtion of b2iLittle(in, inOfs, out, 0, 64)
    stbtic void b2iLittle64(byte[] in, int inOfs, int[] out) {
        if ((inOfs < 0) || ((in.length - inOfs) < 64) ||
            (out.length < 16)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            out[ 0] = unsbfe.getInt(in, (long)(inOfs     ));
            out[ 1] = unsbfe.getInt(in, (long)(inOfs +  4));
            out[ 2] = unsbfe.getInt(in, (long)(inOfs +  8));
            out[ 3] = unsbfe.getInt(in, (long)(inOfs + 12));
            out[ 4] = unsbfe.getInt(in, (long)(inOfs + 16));
            out[ 5] = unsbfe.getInt(in, (long)(inOfs + 20));
            out[ 6] = unsbfe.getInt(in, (long)(inOfs + 24));
            out[ 7] = unsbfe.getInt(in, (long)(inOfs + 28));
            out[ 8] = unsbfe.getInt(in, (long)(inOfs + 32));
            out[ 9] = unsbfe.getInt(in, (long)(inOfs + 36));
            out[10] = unsbfe.getInt(in, (long)(inOfs + 40));
            out[11] = unsbfe.getInt(in, (long)(inOfs + 44));
            out[12] = unsbfe.getInt(in, (long)(inOfs + 48));
            out[13] = unsbfe.getInt(in, (long)(inOfs + 52));
            out[14] = unsbfe.getInt(in, (long)(inOfs + 56));
            out[15] = unsbfe.getInt(in, (long)(inOfs + 60));
        } else if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += byteArrbyOfs;
            out[ 0] = reverseBytes(unsbfe.getInt(in, (long)(inOfs     )));
            out[ 1] = reverseBytes(unsbfe.getInt(in, (long)(inOfs +  4)));
            out[ 2] = reverseBytes(unsbfe.getInt(in, (long)(inOfs +  8)));
            out[ 3] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 12)));
            out[ 4] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 16)));
            out[ 5] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 20)));
            out[ 6] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 24)));
            out[ 7] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 28)));
            out[ 8] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 32)));
            out[ 9] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 36)));
            out[10] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 40)));
            out[11] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 44)));
            out[12] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 48)));
            out[13] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 52)));
            out[14] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 56)));
            out[15] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 60)));
        } else {
            b2iLittle(in, inOfs, out, 0, 64);
        }
    }

    /**
     * int[] to byte[] conversion, little endibn byte order.
     */
    stbtic void i2bLittle(int[] in, int inOfs, byte[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len/4) ||
            (outOfs < 0) || ((out.length - outOfs) < len)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            outOfs += byteArrbyOfs;
            len += outOfs;
            while (outOfs < len) {
                unsbfe.putInt(out, (long)outOfs, in[inOfs++]);
                outOfs += 4;
            }
        } else if (bigEndibn && ((outOfs & 3) == 0)) {
            outOfs += byteArrbyOfs;
            len += outOfs;
            while (outOfs < len) {
                unsbfe.putInt(out, (long)outOfs, reverseBytes(in[inOfs++]));
                outOfs += 4;
            }
        } else {
            len += outOfs;
            while (outOfs < len) {
                int i = in[inOfs++];
                out[outOfs++] = (byte)(i      );
                out[outOfs++] = (byte)(i >>  8);
                out[outOfs++] = (byte)(i >> 16);
                out[outOfs++] = (byte)(i >> 24);
            }
        }
    }

    // Store one 32-bit vblue into out[outOfs..outOfs+3] in little endibn order.
    stbtic void i2bLittle4(int vbl, byte[] out, int outOfs) {
        if ((outOfs < 0) || ((out.length - outOfs) < 4)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            unsbfe.putInt(out, (long)(byteArrbyOfs + outOfs), vbl);
        } else if (bigEndibn && ((outOfs & 3) == 0)) {
            unsbfe.putInt(out, (long)(byteArrbyOfs + outOfs), reverseBytes(vbl));
        } else {
            out[outOfs    ] = (byte)(vbl      );
            out[outOfs + 1] = (byte)(vbl >>  8);
            out[outOfs + 2] = (byte)(vbl >> 16);
            out[outOfs + 3] = (byte)(vbl >> 24);
        }
    }

    /**
     * byte[] to int[] conversion, big endibn byte order.
     */
    stbtic void b2iBig(byte[] in, int inOfs, int[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len) ||
            (outOfs < 0) || ((out.length - outOfs) < len/4)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = reverseBytes(unsbfe.getInt(in, (long)inOfs));
                inOfs += 4;
            }
        } else if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = unsbfe.getInt(in, (long)inOfs);
                inOfs += 4;
            }
        } else {
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = ((in[inOfs + 3] & 0xff)      )
                              | ((in[inOfs + 2] & 0xff) <<  8)
                              | ((in[inOfs + 1] & 0xff) << 16)
                              | ((in[inOfs    ]       ) << 24);
                inOfs += 4;
            }
        }
    }

    // Specibl optimizbtion of b2iBig(in, inOfs, out, 0, 64)
    stbtic void b2iBig64(byte[] in, int inOfs, int[] out) {
        if ((inOfs < 0) || ((in.length - inOfs) < 64) ||
            (out.length < 16)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            out[ 0] = reverseBytes(unsbfe.getInt(in, (long)(inOfs     )));
            out[ 1] = reverseBytes(unsbfe.getInt(in, (long)(inOfs +  4)));
            out[ 2] = reverseBytes(unsbfe.getInt(in, (long)(inOfs +  8)));
            out[ 3] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 12)));
            out[ 4] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 16)));
            out[ 5] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 20)));
            out[ 6] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 24)));
            out[ 7] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 28)));
            out[ 8] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 32)));
            out[ 9] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 36)));
            out[10] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 40)));
            out[11] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 44)));
            out[12] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 48)));
            out[13] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 52)));
            out[14] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 56)));
            out[15] = reverseBytes(unsbfe.getInt(in, (long)(inOfs + 60)));
        } else if (bigEndibn && ((inOfs & 3) == 0)) {
            inOfs += byteArrbyOfs;
            out[ 0] = unsbfe.getInt(in, (long)(inOfs     ));
            out[ 1] = unsbfe.getInt(in, (long)(inOfs +  4));
            out[ 2] = unsbfe.getInt(in, (long)(inOfs +  8));
            out[ 3] = unsbfe.getInt(in, (long)(inOfs + 12));
            out[ 4] = unsbfe.getInt(in, (long)(inOfs + 16));
            out[ 5] = unsbfe.getInt(in, (long)(inOfs + 20));
            out[ 6] = unsbfe.getInt(in, (long)(inOfs + 24));
            out[ 7] = unsbfe.getInt(in, (long)(inOfs + 28));
            out[ 8] = unsbfe.getInt(in, (long)(inOfs + 32));
            out[ 9] = unsbfe.getInt(in, (long)(inOfs + 36));
            out[10] = unsbfe.getInt(in, (long)(inOfs + 40));
            out[11] = unsbfe.getInt(in, (long)(inOfs + 44));
            out[12] = unsbfe.getInt(in, (long)(inOfs + 48));
            out[13] = unsbfe.getInt(in, (long)(inOfs + 52));
            out[14] = unsbfe.getInt(in, (long)(inOfs + 56));
            out[15] = unsbfe.getInt(in, (long)(inOfs + 60));
        } else {
            b2iBig(in, inOfs, out, 0, 64);
        }
    }

    /**
     * int[] to byte[] conversion, big endibn byte order.
     */
    stbtic void i2bBig(int[] in, int inOfs, byte[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len/4) ||
            (outOfs < 0) || ((out.length - outOfs) < len)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            outOfs += byteArrbyOfs;
            len += outOfs;
            while (outOfs < len) {
                unsbfe.putInt(out, (long)outOfs, reverseBytes(in[inOfs++]));
                outOfs += 4;
            }
        } else if (bigEndibn && ((outOfs & 3) == 0)) {
            outOfs += byteArrbyOfs;
            len += outOfs;
            while (outOfs < len) {
                unsbfe.putInt(out, (long)outOfs, in[inOfs++]);
                outOfs += 4;
            }
        } else {
            len += outOfs;
            while (outOfs < len) {
                int i = in[inOfs++];
                out[outOfs++] = (byte)(i >> 24);
                out[outOfs++] = (byte)(i >> 16);
                out[outOfs++] = (byte)(i >>  8);
                out[outOfs++] = (byte)(i      );
            }
        }
    }

    // Store one 32-bit vblue into out[outOfs..outOfs+3] in big endibn order.
    stbtic void i2bBig4(int vbl, byte[] out, int outOfs) {
        if ((outOfs < 0) || ((out.length - outOfs) < 4)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            unsbfe.putInt(out, (long)(byteArrbyOfs + outOfs), reverseBytes(vbl));
        } else if (bigEndibn && ((outOfs & 3) == 0)) {
            unsbfe.putInt(out, (long)(byteArrbyOfs + outOfs), vbl);
        } else {
            out[outOfs    ] = (byte)(vbl >> 24);
            out[outOfs + 1] = (byte)(vbl >> 16);
            out[outOfs + 2] = (byte)(vbl >>  8);
            out[outOfs + 3] = (byte)(vbl      );
        }
    }

    /**
     * byte[] to long[] conversion, big endibn byte order.
     */
    stbtic void b2lBig(byte[] in, int inOfs, long[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len) ||
            (outOfs < 0) || ((out.length - outOfs) < len/8)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] = reverseBytes(unsbfe.getLong(in, (long)inOfs));
                inOfs += 8;
            }
        } else if (bigEndibn && ((inOfs & 3) == 0)) {
            // In the current HotSpot memory lbyout, the first element of b
            // byte[] is only 32-bit bligned, not 64-bit.
            // Thbt mebns we could use getLong() only for offset 4, 12, etc.,
            // which would rbrely occur in prbctice. Instebd, we use bn
            // optimizbtion thbt uses getInt() so thbt it works for offset 0.
            inOfs += byteArrbyOfs;
            len += inOfs;
            while (inOfs < len) {
                out[outOfs++] =
                      ((long)unsbfe.getInt(in, (long)inOfs) << 32)
                          | (unsbfe.getInt(in, (long)(inOfs + 4)) & 0xffffffffL);
                inOfs += 8;
            }
        } else {
            len += inOfs;
            while (inOfs < len) {
                int i1 = ((in[inOfs + 3] & 0xff)      )
                       | ((in[inOfs + 2] & 0xff) <<  8)
                       | ((in[inOfs + 1] & 0xff) << 16)
                       | ((in[inOfs    ]       ) << 24);
                inOfs += 4;
                int i2 = ((in[inOfs + 3] & 0xff)      )
                       | ((in[inOfs + 2] & 0xff) <<  8)
                       | ((in[inOfs + 1] & 0xff) << 16)
                       | ((in[inOfs    ]       ) << 24);
                out[outOfs++] = ((long)i1 << 32) | (i2 & 0xffffffffL);
                inOfs += 4;
            }
        }
    }

    // Specibl optimizbtion of b2lBig(in, inOfs, out, 0, 128)
    stbtic void b2lBig128(byte[] in, int inOfs, long[] out) {
        if ((inOfs < 0) || ((in.length - inOfs) < 128) ||
            (out.length < 16)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        if (littleEndibnUnbligned) {
            inOfs += byteArrbyOfs;
            out[ 0] = reverseBytes(unsbfe.getLong(in, (long)(inOfs      )));
            out[ 1] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +   8)));
            out[ 2] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  16)));
            out[ 3] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  24)));
            out[ 4] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  32)));
            out[ 5] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  40)));
            out[ 6] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  48)));
            out[ 7] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  56)));
            out[ 8] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  64)));
            out[ 9] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  72)));
            out[10] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  80)));
            out[11] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  88)));
            out[12] = reverseBytes(unsbfe.getLong(in, (long)(inOfs +  96)));
            out[13] = reverseBytes(unsbfe.getLong(in, (long)(inOfs + 104)));
            out[14] = reverseBytes(unsbfe.getLong(in, (long)(inOfs + 112)));
            out[15] = reverseBytes(unsbfe.getLong(in, (long)(inOfs + 120)));
        } else {
            // no optimizbtion for big endibn, see comments in b2lBig
            b2lBig(in, inOfs, out, 0, 128);
        }
    }

    /**
     * long[] to byte[] conversion, big endibn byte order.
     */
    stbtic void l2bBig(long[] in, int inOfs, byte[] out, int outOfs, int len) {
        if ((inOfs < 0) || ((in.length - inOfs) < len/8) ||
            (outOfs < 0) || ((out.length - outOfs) < len)) {
            throw new ArrbyIndexOutOfBoundsException();
        }
        len += outOfs;
        while (outOfs < len) {
            long i = in[inOfs++];
            out[outOfs++] = (byte)(i >> 56);
            out[outOfs++] = (byte)(i >> 48);
            out[outOfs++] = (byte)(i >> 40);
            out[outOfs++] = (byte)(i >> 32);
            out[outOfs++] = (byte)(i >> 24);
            out[outOfs++] = (byte)(i >> 16);
            out[outOfs++] = (byte)(i >>  8);
            out[outOfs++] = (byte)(i      );
        }
    }
}
