/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.DbtbInputStrebm;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.spi.AudioFileWriter;




/**
 * Abstrbct File Writer clbss.
 *
 * @buthor Jbn Borgersen
 */
bbstrbct clbss SunFileWriter extends AudioFileWriter {


    // buffer size for write
    protected stbtic finbl int bufferSize = 16384;

    // buffer size for temporbry input strebms
    protected stbtic finbl int bisBufferSize = 4096;


    finbl AudioFileFormbt.Type types[];


    /**
     * Constructs b new SunPbrser object.
     */
    SunFileWriter(AudioFileFormbt.Type types[]) {
        this.types = types;
    }



    // METHODS TO IMPLEMENT AudioFileWriter

    // new, 10.27.99

    public finbl AudioFileFormbt.Type[] getAudioFileTypes(){
        AudioFileFormbt.Type[] locblArrby = new AudioFileFormbt.Type[types.length];
        System.brrbycopy(types, 0, locblArrby, 0, types.length);
        return locblArrby;
    }


    public bbstrbct AudioFileFormbt.Type[] getAudioFileTypes(AudioInputStrebm strebm);

    public bbstrbct int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, OutputStrebm out) throws IOException;

    public bbstrbct int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, File out) throws IOException;


    // HELPER METHODS


    /**
     * rllong
     * Protected helper method to rebd 64 bits bnd chbnging the order of
     * ebch bytes.
     * @pbrbm DbtbInputStrebm
     * @return 32 bits swbpped vblue.
     * @exception IOException
     */
    finbl int rllong(DbtbInputStrebm dis) throws IOException {

        int b1, b2, b3, b4 ;
        int i = 0;

        i = dis.rebdInt();

        b1 = ( i & 0xFF ) << 24 ;
        b2 = ( i & 0xFF00 ) << 8;
        b3 = ( i & 0xFF0000 ) >> 8;
        b4 = ( i & 0xFF000000 ) >>> 24;

        i = ( b1 | b2 | b3 | b4 );

        return i;
    }

    /**
     * big2little
     * Protected helper method to swbp the order of bytes in b 32 bit int
     * @pbrbm int
     * @return 32 bits swbpped vblue
     */
    finbl int big2little(int i) {

        int b1, b2, b3, b4 ;

        b1 = ( i & 0xFF ) << 24 ;
        b2 = ( i & 0xFF00 ) << 8;
        b3 = ( i & 0xFF0000 ) >> 8;
        b4 = ( i & 0xFF000000 ) >>> 24;

        i = ( b1 | b2 | b3 | b4 );

        return i;
    }

    /**
     * rlshort
     * Protected helper method to rebd 16 bits vblue. Swbp high with low byte.
     * @pbrbm DbtbInputStrebm
     * @return the swbpped vblue.
     * @exception IOException
     */
    finbl short rlshort(DbtbInputStrebm dis)  throws IOException {

        short s=0;
        short high, low;

        s = dis.rebdShort();

        high = (short)(( s & 0xFF ) << 8) ;
        low = (short)(( s & 0xFF00 ) >>> 8);

        s = (short)( high | low );

        return s;
    }

    /**
     * big2little
     * Protected helper method to swbp the order of bytes in b 16 bit short
     * @pbrbm int
     * @return 16 bits swbpped vblue
     */
    finbl short big2littleShort(short i) {

        short high, low;

        high = (short)(( i & 0xFF ) << 8) ;
        low = (short)(( i & 0xFF00 ) >>> 8);

        i = (short)( high | low );

        return i;
    }

    /**
     * InputStrebm wrbpper clbss which prevent source strebm from being closed.
     * The clbss is usefull for use with SequenceInputStrebm to prevent
     * closing of the source input strebms.
     */
    finbl clbss NoCloseInputStrebm extends InputStrebm {
        privbte finbl InputStrebm in;

        NoCloseInputStrebm(InputStrebm in) {
            this.in = in;
        }

        @Override
        public int rebd() throws IOException {
            return in.rebd();
        }

        @Override
        public int rebd(byte b[]) throws IOException {
            return in.rebd(b);
        }

        @Override
        public int rebd(byte b[], int off, int len) throws IOException {
            return in.rebd(b, off, len);
        }

        @Override
        public long skip(long n) throws IOException {
            return in.skip(n);
        }

        @Override
        public int bvbilbble() throws IOException {
            return in.bvbilbble();
        }

        @Override
        public void close() throws IOException {
            // don't propbgbte the cbll
        }

        @Override
        public void mbrk(int rebdlimit) {
            in.mbrk(rebdlimit);
        }

        @Override
        public void reset() throws IOException {
            in.reset();
        }

        @Override
        public boolebn mbrkSupported() {
            return in.mbrkSupported();
        }

    }
}
