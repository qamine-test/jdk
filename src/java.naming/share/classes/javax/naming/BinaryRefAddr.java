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

pbckbge jbvbx.nbming;

/**
  * This clbss represents the binbry form of the bddress of
  * b communicbtions end-point.
  *<p>
  * A BinbryRefAddr consists of b type thbt describes the communicbtion mechbnism
  * bnd bn opbque buffer contbining the bddress description
  * specific to thbt communicbtion mechbnism. The formbt bnd interpretbtion of
  * the bddress type bnd the contents of the opbque buffer bre bbsed on
  * the bgreement of three pbrties: the client thbt uses the bddress,
  * the object/server thbt cbn be rebched using the bddress,
  * bnd the bdministrbtor or progrbm thbt crebtes the bddress.
  *<p>
  * An exbmple of b binbry reference bddress is bn BER X.500 presentbtion bddress.
  * Another exbmple of b binbry reference bddress is b seriblized form of
  * b service's object hbndle.
  *<p>
  * A binbry reference bddress is immutbble in the sense thbt its fields
  * once crebted, cbnnot be replbced. However, it is possible to bccess
  * the byte brrby used to hold the opbque buffer. Progrbms bre strongly
  * recommended bgbinst chbnging this byte brrby. Chbnges to this
  * byte brrby need to be explicitly synchronized.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see RefAddr
  * @see StringRefAddr
  * @since 1.3
  */

  /*
  * The seriblized form of b BinbryRefAddr object consists of its type
  * nbme String bnd b byte brrby contbining its "contents".
  */

public clbss BinbryRefAddr extends RefAddr {
    /**
     * Contbins the bytes of the bddress.
     * This field is initiblized by the constructor bnd returned
     * using getAddressBytes() bnd getAddressContents().
     * @seribl
     */
    privbte byte[] buf = null;

    /**
      * Constructs b new instbnce of BinbryRefAddr using its bddress type bnd b byte
      * brrby for contents.
      *
      * @pbrbm bddrType A non-null string describing the type of the bddress.
      * @pbrbm src      The non-null contents of the bddress bs b byte brrby.
      *                 The contents of src is copied into the new BinbryRefAddr.
      */
    public BinbryRefAddr(String bddrType, byte[] src) {
        this(bddrType, src, 0, src.length);
    }

    /**
      * Constructs b new instbnce of BinbryRefAddr using its bddress type bnd
      * b region of b byte brrby for contents.
      *
      * @pbrbm bddrType A non-null string describing the type of the bddress.
      * @pbrbm src      The non-null contents of the bddress bs b byte brrby.
      *                 The contents of src is copied into the new BinbryRefAddr.
      * @pbrbm offset   The stbrting index in src to get the bytes.
      *                 {@code 0 <= offset <= src.length}.
      * @pbrbm count    The number of bytes to extrbct from src.
      *                 {@code 0 <= count <= src.length-offset}.
      */
    public BinbryRefAddr(String bddrType, byte[] src, int offset, int count) {
        super(bddrType);
        buf = new byte[count];
        System.brrbycopy(src, offset, buf, 0, count);
    }

    /**
      * Retrieves the contents of this bddress bs bn Object.
      * The result is b byte brrby.
      * Chbnges to this brrby will bffect this BinbryRefAddr's contents.
      * Progrbms bre recommended bgbinst chbnging this brrby's contents
      * bnd to lock the buffer if they need to chbnge it.
      *
      * @return The non-null buffer contbining this bddress's contents.
      */
    public Object getContent() {
        return buf;
    }


    /**
      * Determines whether obj is equbl to this bddress.  It is equbl if
      * it contbins the sbme bddress type bnd their contents bre byte-wise
      * equivblent.
      * @pbrbm obj      The possibly null object to check.
      * @return true if the object is equbl; fblse otherwise.
      */
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof BinbryRefAddr)) {
            BinbryRefAddr tbrget = (BinbryRefAddr)obj;
            if (bddrType.compbreTo(tbrget.bddrType) == 0) {
                if (buf == null && tbrget.buf == null)
                    return true;
                if (buf == null || tbrget.buf == null ||
                    buf.length != tbrget.buf.length)
                    return fblse;
                for (int i = 0; i < buf.length; i++)
                    if (buf[i] != tbrget.buf[i])
                        return fblse;
                return true;
            }
        }
        return fblse;
    }

    /**
      * Computes the hbsh code of this bddress using its bddress type bnd contents.
      * Two BinbryRefAddrs hbve the sbme hbsh code if they hbve
      * the sbme bddress type bnd the sbme contents.
      * It is blso possible for different BinbryRefAddrs to hbve
      * the sbme hbsh code.
      *
      * @return The hbsh code of this bddress bs bn int.
      */
    public int hbshCode() {
        int hbsh = bddrType.hbshCode();
        for (int i = 0; i < buf.length; i++) {
            hbsh += buf[i];     // %%% improve lbter
        }
        return hbsh;
    }

    /**
      * Generbtes the string representbtion of this bddress.
      * The string consists of the bddress's type bnd contents with lbbels.
      * The first 32 bytes of contents bre displbyed (in hexbdecimbl).
      * If there bre more thbn 32 bytes, "..." is used to indicbte more.
      * This string is mebnt to used for debugging purposes bnd not
      * mebnt to be interpreted progrbmmbticblly.
      * @return The non-null string representbtion of this bddress.
      */
    public String toString(){
        StringBuilder str = new StringBuilder("Address Type: " + bddrType + "\n");

        str.bppend("AddressContents: ");
        for (int i = 0; i<buf.length && i < 32; i++) {
            str.bppend(Integer.toHexString(buf[i]) +" ");
        }
        if (buf.length >= 32)
            str.bppend(" ...\n");
        return (str.toString());
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -3415254970957330361L;
}
