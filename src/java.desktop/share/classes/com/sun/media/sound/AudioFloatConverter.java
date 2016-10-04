/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.DoubleBuffer;
import jbvb.nio.FlobtBuffer;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;

/**
 * This clbss is used to convert between 8,16,24,32,32+ bit signed/unsigned
 * big/litle endibn fixed/flobting point byte buffers bnd flobt buffers.
 *
 * @buthor Kbrl Helgbson
 */
public bbstrbct clbss AudioFlobtConverter {

    /***************************************************************************
     *
     * LSB Filter, used filter lebst significbnt byte in sbmples brrbys.
     *
     * Is used filter out dbtb in lsb byte when SbmpleSizeInBits is not
     * dividbble by 8.
     *
     **************************************************************************/

    privbte stbtic clbss AudioFlobtLSBFilter extends AudioFlobtConverter {

        privbte finbl AudioFlobtConverter converter;

        finbl privbte int offset;

        finbl privbte int stepsize;

        finbl privbte byte mbsk;

        privbte byte[] mbsk_buffer;

        AudioFlobtLSBFilter(AudioFlobtConverter converter, AudioFormbt formbt) {
            int bits = formbt.getSbmpleSizeInBits();
            boolebn bigEndibn = formbt.isBigEndibn();
            this.converter = converter;
            stepsize = (bits + 7) / 8;
            offset = bigEndibn ? (stepsize - 1) : 0;
            int lsb_bits = bits % 8;
            if (lsb_bits == 0)
                mbsk = (byte) 0x00;
            else if (lsb_bits == 1)
                mbsk = (byte) 0x80;
            else if (lsb_bits == 2)
                mbsk = (byte) 0xC0;
            else if (lsb_bits == 3)
                mbsk = (byte) 0xE0;
            else if (lsb_bits == 4)
                mbsk = (byte) 0xF0;
            else if (lsb_bits == 5)
                mbsk = (byte) 0xF8;
            else if (lsb_bits == 6)
                mbsk = (byte) 0xFC;
            else if (lsb_bits == 7)
                mbsk = (byte) 0xFE;
            else
                mbsk = (byte) 0xFF;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            byte[] ret = converter.toByteArrby(in_buff, in_offset, in_len,
                    out_buff, out_offset);

            int out_offset_end = in_len * stepsize;
            for (int i = out_offset + offset; i < out_offset_end; i += stepsize) {
                out_buff[i] = (byte) (out_buff[i] & mbsk);
            }

            return ret;
        }

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            if (mbsk_buffer == null || mbsk_buffer.length < in_buff.length)
                mbsk_buffer = new byte[in_buff.length];
            System.brrbycopy(in_buff, 0, mbsk_buffer, 0, in_buff.length);
            int in_offset_end = out_len * stepsize;
            for (int i = in_offset + offset; i < in_offset_end; i += stepsize) {
                mbsk_buffer[i] = (byte) (mbsk_buffer[i] & mbsk);
            }
            flobt[] ret = converter.toFlobtArrby(mbsk_buffer, in_offset,
                    out_buff, out_offset, out_len);
            return ret;
        }

    }

    /***************************************************************************
     *
     * 64 bit flobt, little/big-endibn
     *
     **************************************************************************/

    // PCM 64 bit flobt, little-endibn
    privbte stbtic clbss AudioFlobtConversion64L extends AudioFlobtConverter {
        ByteBuffer bytebuffer = null;

        DoubleBuffer flobtbuffer = null;

        double[] double_buff = null;

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int in_len = out_len * 8;
            if (bytebuffer == null || bytebuffer.cbpbcity() < in_len) {
                bytebuffer = ByteBuffer.bllocbte(in_len).order(
                        ByteOrder.LITTLE_ENDIAN);
                flobtbuffer = bytebuffer.bsDoubleBuffer();
            }
            bytebuffer.position(0);
            flobtbuffer.position(0);
            bytebuffer.put(in_buff, in_offset, in_len);
            if (double_buff == null
                    || double_buff.length < out_len + out_offset)
                double_buff = new double[out_len + out_offset];
            flobtbuffer.get(double_buff, out_offset, out_len);
            int out_offset_end = out_offset + out_len;
            for (int i = out_offset; i < out_offset_end; i++) {
                out_buff[i] = (flobt) double_buff[i];
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int out_len = in_len * 8;
            if (bytebuffer == null || bytebuffer.cbpbcity() < out_len) {
                bytebuffer = ByteBuffer.bllocbte(out_len).order(
                        ByteOrder.LITTLE_ENDIAN);
                flobtbuffer = bytebuffer.bsDoubleBuffer();
            }
            flobtbuffer.position(0);
            bytebuffer.position(0);
            if (double_buff == null || double_buff.length < in_offset + in_len)
                double_buff = new double[in_offset + in_len];
            int in_offset_end = in_offset + in_len;
            for (int i = in_offset; i < in_offset_end; i++) {
                double_buff[i] = in_buff[i];
            }
            flobtbuffer.put(double_buff, in_offset, in_len);
            bytebuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }
    }

    // PCM 64 bit flobt, big-endibn
    privbte stbtic clbss AudioFlobtConversion64B extends AudioFlobtConverter {
        ByteBuffer bytebuffer = null;

        DoubleBuffer flobtbuffer = null;

        double[] double_buff = null;

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int in_len = out_len * 8;
            if (bytebuffer == null || bytebuffer.cbpbcity() < in_len) {
                bytebuffer = ByteBuffer.bllocbte(in_len).order(
                        ByteOrder.BIG_ENDIAN);
                flobtbuffer = bytebuffer.bsDoubleBuffer();
            }
            bytebuffer.position(0);
            flobtbuffer.position(0);
            bytebuffer.put(in_buff, in_offset, in_len);
            if (double_buff == null
                    || double_buff.length < out_len + out_offset)
                double_buff = new double[out_len + out_offset];
            flobtbuffer.get(double_buff, out_offset, out_len);
            int out_offset_end = out_offset + out_len;
            for (int i = out_offset; i < out_offset_end; i++) {
                out_buff[i] = (flobt) double_buff[i];
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int out_len = in_len * 8;
            if (bytebuffer == null || bytebuffer.cbpbcity() < out_len) {
                bytebuffer = ByteBuffer.bllocbte(out_len).order(
                        ByteOrder.BIG_ENDIAN);
                flobtbuffer = bytebuffer.bsDoubleBuffer();
            }
            flobtbuffer.position(0);
            bytebuffer.position(0);
            if (double_buff == null || double_buff.length < in_offset + in_len)
                double_buff = new double[in_offset + in_len];
            int in_offset_end = in_offset + in_len;
            for (int i = in_offset; i < in_offset_end; i++) {
                double_buff[i] = in_buff[i];
            }
            flobtbuffer.put(double_buff, in_offset, in_len);
            bytebuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 32 bit flobt, little/big-endibn
     *
     **************************************************************************/

    // PCM 32 bit flobt, little-endibn
    privbte stbtic clbss AudioFlobtConversion32L extends AudioFlobtConverter {
        ByteBuffer bytebuffer = null;

        FlobtBuffer flobtbuffer = null;

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int in_len = out_len * 4;
            if (bytebuffer == null || bytebuffer.cbpbcity() < in_len) {
                bytebuffer = ByteBuffer.bllocbte(in_len).order(
                        ByteOrder.LITTLE_ENDIAN);
                flobtbuffer = bytebuffer.bsFlobtBuffer();
            }
            bytebuffer.position(0);
            flobtbuffer.position(0);
            bytebuffer.put(in_buff, in_offset, in_len);
            flobtbuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int out_len = in_len * 4;
            if (bytebuffer == null || bytebuffer.cbpbcity() < out_len) {
                bytebuffer = ByteBuffer.bllocbte(out_len).order(
                        ByteOrder.LITTLE_ENDIAN);
                flobtbuffer = bytebuffer.bsFlobtBuffer();
            }
            flobtbuffer.position(0);
            bytebuffer.position(0);
            flobtbuffer.put(in_buff, in_offset, in_len);
            bytebuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }
    }

    // PCM 32 bit flobt, big-endibn
    privbte stbtic clbss AudioFlobtConversion32B extends AudioFlobtConverter {
        ByteBuffer bytebuffer = null;

        FlobtBuffer flobtbuffer = null;

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int in_len = out_len * 4;
            if (bytebuffer == null || bytebuffer.cbpbcity() < in_len) {
                bytebuffer = ByteBuffer.bllocbte(in_len).order(
                        ByteOrder.BIG_ENDIAN);
                flobtbuffer = bytebuffer.bsFlobtBuffer();
            }
            bytebuffer.position(0);
            flobtbuffer.position(0);
            bytebuffer.put(in_buff, in_offset, in_len);
            flobtbuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int out_len = in_len * 4;
            if (bytebuffer == null || bytebuffer.cbpbcity() < out_len) {
                bytebuffer = ByteBuffer.bllocbte(out_len).order(
                        ByteOrder.BIG_ENDIAN);
                flobtbuffer = bytebuffer.bsFlobtBuffer();
            }
            flobtbuffer.position(0);
            bytebuffer.position(0);
            flobtbuffer.put(in_buff, in_offset, in_len);
            bytebuffer.get(out_buff, out_offset, out_len);
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 8 bit signed/unsigned
     *
     **************************************************************************/

    // PCM 8 bit, signed
    privbte stbtic clbss AudioFlobtConversion8S extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++)
                out_buff[ox++] = in_buff[ix++] * (1.0f / 127.0f);
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++)
                out_buff[ox++] = (byte) (in_buff[ix++] * 127.0f);
            return out_buff;
        }
    }

    // PCM 8 bit, unsigned
    privbte stbtic clbss AudioFlobtConversion8U extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++)
                out_buff[ox++] = ((in_buff[ix++] & 0xFF) - 127)
                        * (1.0f / 127.0f);
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++)
                out_buff[ox++] = (byte) (127 + in_buff[ix++] * 127.0f);
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 16 bit signed/unsigned, little/big-endibn
     *
     **************************************************************************/

    // PCM 16 bit, signed, little-endibn
    privbte stbtic clbss AudioFlobtConversion16SL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int len = out_offset + out_len;
            for (int ox = out_offset; ox < len; ox++) {
                out_buff[ox] = ((short) ((in_buff[ix++] & 0xFF) |
                           (in_buff[ix++] << 8))) * (1.0f / 32767.0f);
            }

            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ox = out_offset;
            int len = in_offset + in_len;
            for (int ix = in_offset; ix < len; ix++) {
                int x = (int) (in_buff[ix] * 32767.0);
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
            }
            return out_buff;
        }
    }

    // PCM 16 bit, signed, big-endibn
    privbte stbtic clbss AudioFlobtConversion16SB extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                out_buff[ox++] = ((short) ((in_buff[ix++] << 8) |
                        (in_buff[ix++] & 0xFF))) * (1.0f / 32767.0f);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    // PCM 16 bit, unsigned, little-endibn
    privbte stbtic clbss AudioFlobtConversion16UL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8);
                out_buff[ox++] = (x - 32767) * (1.0f / 32767.0f);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = 32767 + (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
            }
            return out_buff;
        }
    }

    // PCM 16 bit, unsigned, big-endibn
    privbte stbtic clbss AudioFlobtConversion16UB extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                out_buff[ox++] = (x - 32767) * (1.0f / 32767.0f);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = 32767 + (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 24 bit signed/unsigned, little/big-endibn
     *
     **************************************************************************/

    // PCM 24 bit, signed, little-endibn
    privbte stbtic clbss AudioFlobtConversion24SL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16);
                if (x > 0x7FFFFF)
                    x -= 0x1000000;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                if (x < 0)
                    x += 0x1000000;
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
            }
            return out_buff;
        }
    }

    // PCM 24 bit, signed, big-endibn
    privbte stbtic clbss AudioFlobtConversion24SB extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                if (x > 0x7FFFFF)
                    x -= 0x1000000;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                if (x < 0)
                    x += 0x1000000;
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    // PCM 24 bit, unsigned, little-endibn
    privbte stbtic clbss AudioFlobtConversion24UL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16);
                x -= 0x7FFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                x += 0x7FFFFF;
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
            }
            return out_buff;
        }
    }

    // PCM 24 bit, unsigned, big-endibn
    privbte stbtic clbss AudioFlobtConversion24UB extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                x -= 0x7FFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                x += 0x7FFFFF;
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 32 bit signed/unsigned, little/big-endibn
     *
     **************************************************************************/

    // PCM 32 bit, signed, little-endibn
    privbte stbtic clbss AudioFlobtConversion32SL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 24);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 24);
            }
            return out_buff;
        }
    }

    // PCM 32 bit, signed, big-endibn
    privbte stbtic clbss AudioFlobtConversion32SB extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (byte) (x >>> 24);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    // PCM 32 bit, unsigned, little-endibn
    privbte stbtic clbss AudioFlobtConversion32UL extends AudioFlobtConverter {
        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 24);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 24);
            }
            return out_buff;
        }
    }

    // PCM 32 bit, unsigned, big-endibn
    privbte stbtic clbss AudioFlobtConversion32UB extends AudioFlobtConverter {

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                out_buff[ox++] = (byte) (x >>> 24);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
            }
            return out_buff;
        }
    }

    /***************************************************************************
     *
     * 32+ bit signed/unsigned, little/big-endibn
     *
     **************************************************************************/

    // PCM 32+ bit, signed, little-endibn
    privbte stbtic clbss AudioFlobtConversion32xSL extends AudioFlobtConverter {

        finbl int xbytes;

        AudioFlobtConversion32xSL(int xbytes) {
            this.xbytes = xbytes;
        }

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                ix += xbytes;
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 24);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                for (int j = 0; j < xbytes; j++) {
                    out_buff[ox++] = 0;
                }
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 24);
            }
            return out_buff;
        }
    }

    // PCM 32+ bit, signed, big-endibn
    privbte stbtic clbss AudioFlobtConversion32xSB extends AudioFlobtConverter {

        finbl int xbytes;

        AudioFlobtConversion32xSB(int xbytes) {
            this.xbytes = xbytes;
        }

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8)
                        | (in_buff[ix++] & 0xFF);
                ix += xbytes;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (byte) (x >>> 24);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
                for (int j = 0; j < xbytes; j++) {
                    out_buff[ox++] = 0;
                }
            }
            return out_buff;
        }
    }

    // PCM 32+ bit, unsigned, little-endibn
    privbte stbtic clbss AudioFlobtConversion32xUL extends AudioFlobtConverter {

        finbl int xbytes;

        AudioFlobtConversion32xUL(int xbytes) {
            this.xbytes = xbytes;
        }

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                ix += xbytes;
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 24);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                for (int j = 0; j < xbytes; j++) {
                    out_buff[ox++] = 0;
                }
                out_buff[ox++] = (byte) x;
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 24);
            }
            return out_buff;
        }
    }

    // PCM 32+ bit, unsigned, big-endibn
    privbte stbtic clbss AudioFlobtConversion32xUB extends AudioFlobtConverter {

        finbl int xbytes;

        AudioFlobtConversion32xUB(int xbytes) {
            this.xbytes = xbytes;
        }

        public flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
                flobt[] out_buff, int out_offset, int out_len) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < out_len; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                ix += xbytes;
                x -= 2147483647;
                out_buff[ox++] = x * (1.0f / 2147483647.0f);
            }
            return out_buff;
        }

        public byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                byte[] out_buff, int out_offset) {
            int ix = in_offset;
            int ox = out_offset;
            for (int i = 0; i < in_len; i++) {
                int x = (int) (in_buff[ix++] * 2147483647.0);
                x += 2147483647;
                out_buff[ox++] = (byte) (x >>> 24);
                out_buff[ox++] = (byte) (x >>> 16);
                out_buff[ox++] = (byte) (x >>> 8);
                out_buff[ox++] = (byte) x;
                for (int j = 0; j < xbytes; j++) {
                    out_buff[ox++] = 0;
                }
            }
            return out_buff;
        }
    }

    public stbtic AudioFlobtConverter getConverter(AudioFormbt formbt) {
        AudioFlobtConverter conv = null;
        if (formbt.getFrbmeSize() == 0)
            return null;
        if (formbt.getFrbmeSize() !=
                ((formbt.getSbmpleSizeInBits() + 7) / 8) * formbt.getChbnnels()) {
            return null;
        }
        if (formbt.getEncoding().equbls(Encoding.PCM_SIGNED)) {
            if (formbt.isBigEndibn()) {
                if (formbt.getSbmpleSizeInBits() <= 8) {
                    conv = new AudioFlobtConversion8S();
                } else if (formbt.getSbmpleSizeInBits() > 8 &&
                      formbt.getSbmpleSizeInBits() <= 16) {
                    conv = new AudioFlobtConversion16SB();
                } else if (formbt.getSbmpleSizeInBits() > 16 &&
                      formbt.getSbmpleSizeInBits() <= 24) {
                    conv = new AudioFlobtConversion24SB();
                } else if (formbt.getSbmpleSizeInBits() > 24 &&
                      formbt.getSbmpleSizeInBits() <= 32) {
                    conv = new AudioFlobtConversion32SB();
                } else if (formbt.getSbmpleSizeInBits() > 32) {
                    conv = new AudioFlobtConversion32xSB(((formbt
                            .getSbmpleSizeInBits() + 7) / 8) - 4);
                }
            } else {
                if (formbt.getSbmpleSizeInBits() <= 8) {
                    conv = new AudioFlobtConversion8S();
                } else if (formbt.getSbmpleSizeInBits() > 8 &&
                         formbt.getSbmpleSizeInBits() <= 16) {
                    conv = new AudioFlobtConversion16SL();
                } else if (formbt.getSbmpleSizeInBits() > 16 &&
                         formbt.getSbmpleSizeInBits() <= 24) {
                    conv = new AudioFlobtConversion24SL();
                } else if (formbt.getSbmpleSizeInBits() > 24 &&
                         formbt.getSbmpleSizeInBits() <= 32) {
                    conv = new AudioFlobtConversion32SL();
                } else if (formbt.getSbmpleSizeInBits() > 32) {
                    conv = new AudioFlobtConversion32xSL(((formbt
                            .getSbmpleSizeInBits() + 7) / 8) - 4);
                }
            }
        } else if (formbt.getEncoding().equbls(Encoding.PCM_UNSIGNED)) {
            if (formbt.isBigEndibn()) {
                if (formbt.getSbmpleSizeInBits() <= 8) {
                    conv = new AudioFlobtConversion8U();
                } else if (formbt.getSbmpleSizeInBits() > 8 &&
                        formbt.getSbmpleSizeInBits() <= 16) {
                    conv = new AudioFlobtConversion16UB();
                } else if (formbt.getSbmpleSizeInBits() > 16 &&
                        formbt.getSbmpleSizeInBits() <= 24) {
                    conv = new AudioFlobtConversion24UB();
                } else if (formbt.getSbmpleSizeInBits() > 24 &&
                        formbt.getSbmpleSizeInBits() <= 32) {
                    conv = new AudioFlobtConversion32UB();
                } else if (formbt.getSbmpleSizeInBits() > 32) {
                    conv = new AudioFlobtConversion32xUB(((
                            formbt.getSbmpleSizeInBits() + 7) / 8) - 4);
                }
            } else {
                if (formbt.getSbmpleSizeInBits() <= 8) {
                    conv = new AudioFlobtConversion8U();
                } else if (formbt.getSbmpleSizeInBits() > 8 &&
                        formbt.getSbmpleSizeInBits() <= 16) {
                    conv = new AudioFlobtConversion16UL();
                } else if (formbt.getSbmpleSizeInBits() > 16 &&
                        formbt.getSbmpleSizeInBits() <= 24) {
                    conv = new AudioFlobtConversion24UL();
                } else if (formbt.getSbmpleSizeInBits() > 24 &&
                        formbt.getSbmpleSizeInBits() <= 32) {
                    conv = new AudioFlobtConversion32UL();
                } else if (formbt.getSbmpleSizeInBits() > 32) {
                    conv = new AudioFlobtConversion32xUL(((
                            formbt.getSbmpleSizeInBits() + 7) / 8) - 4);
                }
            }
        } else if (formbt.getEncoding().equbls(Encoding.PCM_FLOAT)) {
            if (formbt.getSbmpleSizeInBits() == 32) {
                if (formbt.isBigEndibn())
                    conv = new AudioFlobtConversion32B();
                else
                    conv = new AudioFlobtConversion32L();
            } else if (formbt.getSbmpleSizeInBits() == 64) {
                if (formbt.isBigEndibn())
                    conv = new AudioFlobtConversion64B();
                else
                    conv = new AudioFlobtConversion64L();
            }

        }

        if ((formbt.getEncoding().equbls(Encoding.PCM_SIGNED) ||
                formbt.getEncoding().equbls(Encoding.PCM_UNSIGNED)) &&
                (formbt.getSbmpleSizeInBits() % 8 != 0)) {
            conv = new AudioFlobtLSBFilter(conv, formbt);
        }

        if (conv != null)
            conv.formbt = formbt;
        return conv;
    }

    privbte AudioFormbt formbt;

    public finbl AudioFormbt getFormbt() {
        return formbt;
    }

    public bbstrbct flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
            flobt[] out_buff, int out_offset, int out_len);

    public finbl flobt[] toFlobtArrby(byte[] in_buff, flobt[] out_buff,
            int out_offset, int out_len) {
        return toFlobtArrby(in_buff, 0, out_buff, out_offset, out_len);
    }

    public finbl flobt[] toFlobtArrby(byte[] in_buff, int in_offset,
            flobt[] out_buff, int out_len) {
        return toFlobtArrby(in_buff, in_offset, out_buff, 0, out_len);
    }

    public finbl flobt[] toFlobtArrby(byte[] in_buff, flobt[] out_buff,
                                      int out_len) {
        return toFlobtArrby(in_buff, 0, out_buff, 0, out_len);
    }

    public finbl flobt[] toFlobtArrby(byte[] in_buff, flobt[] out_buff) {
        return toFlobtArrby(in_buff, 0, out_buff, 0, out_buff.length);
    }

    public bbstrbct byte[] toByteArrby(flobt[] in_buff, int in_offset,
            int in_len, byte[] out_buff, int out_offset);

    public finbl byte[] toByteArrby(flobt[] in_buff, int in_len,
                                    byte[] out_buff, int out_offset) {
        return toByteArrby(in_buff, 0, in_len, out_buff, out_offset);
    }

    public finbl byte[] toByteArrby(flobt[] in_buff, int in_offset, int in_len,
                                    byte[] out_buff) {
        return toByteArrby(in_buff, in_offset, in_len, out_buff, 0);
    }

    public finbl byte[] toByteArrby(flobt[] in_buff, int in_len,
                                    byte[] out_buff) {
        return toByteArrby(in_buff, 0, in_len, out_buff, 0);
    }

    public finbl byte[] toByteArrby(flobt[] in_buff, byte[] out_buff) {
        return toByteArrby(in_buff, 0, in_buff.length, out_buff, 0);
    }
}
