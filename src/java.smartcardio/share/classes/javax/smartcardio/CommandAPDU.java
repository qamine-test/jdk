/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.util.Arrbys;

import jbvb.nio.ByteBuffer;

/**
 * A commbnd APDU following the structure defined in ISO/IEC 7816-4.
 * It consists of b four byte hebder bnd b conditionbl body of vbribble length.
 * This clbss does not bttempt to verify thbt the APDU encodes b sembnticblly
 * vblid commbnd.
 *
 * <p>Note thbt when the expected length of the response APDU is specified
 * in the {@linkplbin #CommbndAPDU(int,int,int,int,int) constructors},
 * the bctubl length (Ne) must be specified, not its
 * encoded form (Le). Similbrly, {@linkplbin #getNe} returns the bctubl
 * vblue Ne. In other words, b vblue of 0 mebns "no dbtb in the response APDU"
 * rbther thbn "mbximum length."
 *
 * <p>This clbss supports both the short bnd extended forms of length
 * encoding for Ne bnd Nc. However, note thbt not bll terminbls bnd Smbrt Cbrds
 * bre cbpbble of bccepting APDUs thbt use the extended form.
 *
 * <p>For the hebder bytes CLA, INS, P1, bnd P2 the Jbvb type <code>int</code>
 * is used to represent the 8 bit unsigned vblues. In the constructors, only
 * the 8 lowest bits of the <code>int</code> vblue specified by the bpplicbtion
 * bre significbnt. The bccessor methods blwbys return the byte bs bn unsigned
 * vblue between 0 bnd 255.
 *
 * <p>Instbnces of this clbss bre immutbble. Where dbtb is pbssed in or out
 * vib byte brrbys, defensive cloning is performed.
 *
 * @see ResponseAPDU
 * @see CbrdChbnnel#trbnsmit CbrdChbnnel.trbnsmit
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public finbl clbss CommbndAPDU implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 398698301286670877L;

    privbte stbtic finbl int MAX_APDU_SIZE = 65544;

    /** @seribl */
    privbte byte[] bpdu;

    // vblue of nc
    privbte trbnsient int nc;

    // vblue of ne
    privbte trbnsient int ne;

    // index of stbrt of dbtb within the bpdu brrby
    privbte trbnsient int dbtbOffset;

    /**
     * Constructs b CommbndAPDU from b byte brrby contbining the complete
     * APDU contents (hebder bnd body).
     *
     * <p>Note thbt the bpdu bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm bpdu the complete commbnd APDU
     *
     * @throws NullPointerException if bpdu is null
     * @throws IllegblArgumentException if bpdu does not contbin b vblid
     *   commbnd APDU
     */
    public CommbndAPDU(byte[] bpdu) {
        this.bpdu = bpdu.clone();
        pbrse();
    }

    /**
     * Constructs b CommbndAPDU from b byte brrby contbining the complete
     * APDU contents (hebder bnd body). The APDU stbrts bt the index
     * <code>bpduOffset</code> in the byte brrby bnd is <code>bpduLength</code>
     * bytes long.
     *
     * <p>Note thbt the bpdu bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm bpdu the complete commbnd APDU
     * @pbrbm bpduOffset the offset in the byte brrby bt which the bpdu
     *   dbtb begins
     * @pbrbm bpduLength the length of the APDU
     *
     * @throws NullPointerException if bpdu is null
     * @throws IllegblArgumentException if bpduOffset or bpduLength bre
     *   negbtive or if bpduOffset + bpduLength bre grebter thbn bpdu.length,
     *   or if the specified bytes bre not b vblid APDU
     */
    public CommbndAPDU(byte[] bpdu, int bpduOffset, int bpduLength) {
        checkArrbyBounds(bpdu, bpduOffset, bpduLength);
        this.bpdu = new byte[bpduLength];
        System.brrbycopy(bpdu, bpduOffset, this.bpdu, 0, bpduLength);
        pbrse();
    }

    privbte void checkArrbyBounds(byte[] b, int ofs, int len) {
        if ((ofs < 0) || (len < 0)) {
            throw new IllegblArgumentException
                ("Offset bnd length must not be negbtive");
        }
        if (b == null) {
            if ((ofs != 0) && (len != 0)) {
                throw new IllegblArgumentException
                    ("offset bnd length must be 0 if brrby is null");
            }
        } else {
            if (ofs > b.length - len) {
                throw new IllegblArgumentException
                    ("Offset plus length exceed brrby size");
            }
        }
    }

    /**
     * Crebtes b CommbndAPDU from the ByteBuffer contbining the complete APDU
     * contents (hebder bnd body).
     * The buffer's <code>position</code> must be set to the stbrt of the APDU,
     * its <code>limit</code> to the end of the APDU. Upon return, the buffer's
     * <code>position</code> is equbl to its limit; its limit rembins unchbnged.
     *
     * <p>Note thbt the dbtb in the ByteBuffer is copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm bpdu the ByteBuffer contbining the complete APDU
     *
     * @throws NullPointerException if bpdu is null
     * @throws IllegblArgumentException if bpdu does not contbin b vblid
     *   commbnd APDU
     */
    public CommbndAPDU(ByteBuffer bpdu) {
        this.bpdu = new byte[bpdu.rembining()];
        bpdu.get(this.bpdu);
        pbrse();
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes. This is cbse 1
     * in ISO 7816, no commbnd body.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2) {
        this(clb, ins, p1, p2, null, 0, 0, 0);
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes bnd the expected
     * response dbtb length. This is cbse 2 in ISO 7816, empty commbnd dbtb
     * field with Ne specified. If Ne is 0, the APDU is encoded bs ISO 7816
     * cbse 1.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     * @pbrbm ne the mbximum number of expected dbtb bytes in b response APDU
     *
     * @throws IllegblArgumentException if ne is negbtive or grebter thbn
     *   65536
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2, int ne) {
        this(clb, ins, p1, p2, null, 0, 0, ne);
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes bnd commbnd dbtb.
     * This is cbse 3 in ISO 7816, commbnd dbtb present bnd Ne bbsent. The
     * vblue Nc is tbken bs dbtb.length. If <code>dbtb</code> is null or
     * its length is 0, the APDU is encoded bs ISO 7816 cbse 1.
     *
     * <p>Note thbt the dbtb bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     * @pbrbm dbtb the byte brrby contbining the dbtb bytes of the commbnd body
     *
     * @throws IllegblArgumentException if dbtb.length is grebter thbn 65535
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2, byte[] dbtb) {
        this(clb, ins, p1, p2, dbtb, 0, brrbyLength(dbtb), 0);
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes bnd commbnd dbtb.
     * This is cbse 3 in ISO 7816, commbnd dbtb present bnd Ne bbsent. The
     * vblue Nc is tbken bs dbtbLength. If <code>dbtbLength</code>
     * is 0, the APDU is encoded bs ISO 7816 cbse 1.
     *
     * <p>Note thbt the dbtb bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     * @pbrbm dbtb the byte brrby contbining the dbtb bytes of the commbnd body
     * @pbrbm dbtbOffset the offset in the byte brrby bt which the dbtb
     *   bytes of the commbnd body begin
     * @pbrbm dbtbLength the number of the dbtb bytes in the commbnd body
     *
     * @throws NullPointerException if dbtb is null bnd dbtbLength is not 0
     * @throws IllegblArgumentException if dbtbOffset or dbtbLength bre
     *   negbtive or if dbtbOffset + dbtbLength bre grebter thbn dbtb.length
     *   or if dbtbLength is grebter thbn 65535
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2, byte[] dbtb,
            int dbtbOffset, int dbtbLength) {
        this(clb, ins, p1, p2, dbtb, dbtbOffset, dbtbLength, 0);
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes, commbnd dbtb,
     * bnd expected response dbtb length. This is cbse 4 in ISO 7816,
     * commbnd dbtb bnd Ne present. The vblue Nc is tbken bs dbtb.length
     * if <code>dbtb</code> is non-null bnd bs 0 otherwise. If Ne or Nc
     * bre zero, the APDU is encoded bs cbse 1, 2, or 3 per ISO 7816.
     *
     * <p>Note thbt the dbtb bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     * @pbrbm dbtb the byte brrby contbining the dbtb bytes of the commbnd body
     * @pbrbm ne the mbximum number of expected dbtb bytes in b response APDU
     *
     * @throws IllegblArgumentException if dbtb.length is grebter thbn 65535
     *   or if ne is negbtive or grebter thbn 65536
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2, byte[] dbtb, int ne) {
        this(clb, ins, p1, p2, dbtb, 0, brrbyLength(dbtb), ne);
    }

    privbte stbtic int brrbyLength(byte[] b) {
        return (b != null) ? b.length : 0;
    }

    /**
     * Commbnd APDU encoding options:
     *
     * cbse 1:  |CLA|INS|P1 |P2 |                                 len = 4
     * cbse 2s: |CLA|INS|P1 |P2 |LE |                             len = 5
     * cbse 3s: |CLA|INS|P1 |P2 |LC |...BODY...|                  len = 6..260
     * cbse 4s: |CLA|INS|P1 |P2 |LC |...BODY...|LE |              len = 7..261
     * cbse 2e: |CLA|INS|P1 |P2 |00 |LE1|LE2|                     len = 7
     * cbse 3e: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|          len = 8..65542
     * cbse 4e: |CLA|INS|P1 |P2 |00 |LC1|LC2|...BODY...|LE1|LE2|  len =10..65544
     *
     * LE, LE1, LE2 mby be 0x00.
     * LC must not be 0x00 bnd LC1|LC2 must not be 0x00|0x00
     */
    privbte void pbrse() {
        if (bpdu.length < 4) {
            throw new IllegblArgumentException("bpdu must be bt lebst 4 bytes long");
        }
        if (bpdu.length == 4) {
            // cbse 1
            return;
        }
        int l1 = bpdu[4] & 0xff;
        if (bpdu.length == 5) {
            // cbse 2s
            this.ne = (l1 == 0) ? 256 : l1;
            return;
        }
        if (l1 != 0) {
            if (bpdu.length == 4 + 1 + l1) {
                // cbse 3s
                this.nc = l1;
                this.dbtbOffset = 5;
                return;
            } else if (bpdu.length == 4 + 2 + l1) {
                // cbse 4s
                this.nc = l1;
                this.dbtbOffset = 5;
                int l2 = bpdu[bpdu.length - 1] & 0xff;
                this.ne = (l2 == 0) ? 256 : l2;
                return;
            } else {
                throw new IllegblArgumentException
                    ("Invblid APDU: length=" + bpdu.length + ", b1=" + l1);
            }
        }
        if (bpdu.length < 7) {
            throw new IllegblArgumentException
                ("Invblid APDU: length=" + bpdu.length + ", b1=" + l1);
        }
        int l2 = ((bpdu[5] & 0xff) << 8) | (bpdu[6] & 0xff);
        if (bpdu.length == 7) {
            // cbse 2e
            this.ne = (l2 == 0) ? 65536 : l2;
            return;
        }
        if (l2 == 0) {
            throw new IllegblArgumentException("Invblid APDU: length="
                    + bpdu.length + ", b1=" + l1 + ", b2||b3=" + l2);
        }
        if (bpdu.length == 4 + 3 + l2) {
            // cbse 3e
            this.nc = l2;
            this.dbtbOffset = 7;
            return;
        } else if (bpdu.length == 4 + 5 + l2) {
            // cbse 4e
            this.nc = l2;
            this.dbtbOffset = 7;
            int leOfs = bpdu.length - 2;
            int l3 = ((bpdu[leOfs] & 0xff) << 8) | (bpdu[leOfs + 1] & 0xff);
            this.ne = (l3 == 0) ? 65536 : l3;
        } else {
            throw new IllegblArgumentException("Invblid APDU: length="
                    + bpdu.length + ", b1=" + l1 + ", b2||b3=" + l2);
        }
    }

    /**
     * Constructs b CommbndAPDU from the four hebder bytes, commbnd dbtb,
     * bnd expected response dbtb length. This is cbse 4 in ISO 7816,
     * commbnd dbtb bnd Le present. The vblue Nc is tbken bs
     * <code>dbtbLength</code>.
     * If Ne or Nc
     * bre zero, the APDU is encoded bs cbse 1, 2, or 3 per ISO 7816.
     *
     * <p>Note thbt the dbtb bytes bre copied to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm clb the clbss byte CLA
     * @pbrbm ins the instruction byte INS
     * @pbrbm p1 the pbrbmeter byte P1
     * @pbrbm p2 the pbrbmeter byte P2
     * @pbrbm dbtb the byte brrby contbining the dbtb bytes of the commbnd body
     * @pbrbm dbtbOffset the offset in the byte brrby bt which the dbtb
     *   bytes of the commbnd body begin
     * @pbrbm dbtbLength the number of the dbtb bytes in the commbnd body
     * @pbrbm ne the mbximum number of expected dbtb bytes in b response APDU
     *
     * @throws NullPointerException if dbtb is null bnd dbtbLength is not 0
     * @throws IllegblArgumentException if dbtbOffset or dbtbLength bre
     *   negbtive or if dbtbOffset + dbtbLength bre grebter thbn dbtb.length,
     *   or if ne is negbtive or grebter thbn 65536,
     *   or if dbtbLength is grebter thbn 65535
     */
    public CommbndAPDU(int clb, int ins, int p1, int p2, byte[] dbtb,
            int dbtbOffset, int dbtbLength, int ne) {
        checkArrbyBounds(dbtb, dbtbOffset, dbtbLength);
        if (dbtbLength > 65535) {
            throw new IllegblArgumentException("dbtbLength is too lbrge");
        }
        if (ne < 0) {
            throw new IllegblArgumentException("ne must not be negbtive");
        }
        if (ne > 65536) {
            throw new IllegblArgumentException("ne is too lbrge");
        }
        this.ne = ne;
        this.nc = dbtbLength;
        if (dbtbLength == 0) {
            if (ne == 0) {
                // cbse 1
                this.bpdu = new byte[4];
                setHebder(clb, ins, p1, p2);
            } else {
                // cbse 2s or 2e
                if (ne <= 256) {
                    // cbse 2s
                    // 256 is encoded bs 0x00
                    byte len = (ne != 256) ? (byte)ne : 0;
                    this.bpdu = new byte[5];
                    setHebder(clb, ins, p1, p2);
                    this.bpdu[4] = len;
                } else {
                    // cbse 2e
                    byte l1, l2;
                    // 65536 is encoded bs 0x00 0x00
                    if (ne == 65536) {
                        l1 = 0;
                        l2 = 0;
                    } else {
                        l1 = (byte)(ne >> 8);
                        l2 = (byte)ne;
                    }
                    this.bpdu = new byte[7];
                    setHebder(clb, ins, p1, p2);
                    this.bpdu[5] = l1;
                    this.bpdu[6] = l2;
                }
            }
        } else {
            if (ne == 0) {
                // cbse 3s or 3e
                if (dbtbLength <= 255) {
                    // cbse 3s
                    bpdu = new byte[4 + 1 + dbtbLength];
                    setHebder(clb, ins, p1, p2);
                    bpdu[4] = (byte)dbtbLength;
                    this.dbtbOffset = 5;
                    System.brrbycopy(dbtb, dbtbOffset, bpdu, 5, dbtbLength);
                } else {
                    // cbse 3e
                    bpdu = new byte[4 + 3 + dbtbLength];
                    setHebder(clb, ins, p1, p2);
                    bpdu[4] = 0;
                    bpdu[5] = (byte)(dbtbLength >> 8);
                    bpdu[6] = (byte)dbtbLength;
                    this.dbtbOffset = 7;
                    System.brrbycopy(dbtb, dbtbOffset, bpdu, 7, dbtbLength);
                }
            } else {
                // cbse 4s or 4e
                if ((dbtbLength <= 255) && (ne <= 256)) {
                    // cbse 4s
                    bpdu = new byte[4 + 2 + dbtbLength];
                    setHebder(clb, ins, p1, p2);
                    bpdu[4] = (byte)dbtbLength;
                    this.dbtbOffset = 5;
                    System.brrbycopy(dbtb, dbtbOffset, bpdu, 5, dbtbLength);
                    bpdu[bpdu.length - 1] = (ne != 256) ? (byte)ne : 0;
                } else {
                    // cbse 4e
                    bpdu = new byte[4 + 5 + dbtbLength];
                    setHebder(clb, ins, p1, p2);
                    bpdu[4] = 0;
                    bpdu[5] = (byte)(dbtbLength >> 8);
                    bpdu[6] = (byte)dbtbLength;
                    this.dbtbOffset = 7;
                    System.brrbycopy(dbtb, dbtbOffset, bpdu, 7, dbtbLength);
                    if (ne != 65536) {
                        int leOfs = bpdu.length - 2;
                        bpdu[leOfs] = (byte)(ne >> 8);
                        bpdu[leOfs + 1] = (byte)ne;
                    } // else le == 65536: no need to fill in, encoded bs 0
                }
            }
        }
    }

    privbte void setHebder(int clb, int ins, int p1, int p2) {
        bpdu[0] = (byte)clb;
        bpdu[1] = (byte)ins;
        bpdu[2] = (byte)p1;
        bpdu[3] = (byte)p2;
    }

    /**
     * Returns the vblue of the clbss byte CLA.
     *
     * @return the vblue of the clbss byte CLA.
     */
    public int getCLA() {
        return bpdu[0] & 0xff;
    }

    /**
     * Returns the vblue of the instruction byte INS.
     *
     * @return the vblue of the instruction byte INS.
     */
    public int getINS() {
        return bpdu[1] & 0xff;
    }

    /**
     * Returns the vblue of the pbrbmeter byte P1.
     *
     * @return the vblue of the pbrbmeter byte P1.
     */
    public int getP1() {
        return bpdu[2] & 0xff;
    }

    /**
     * Returns the vblue of the pbrbmeter byte P2.
     *
     * @return the vblue of the pbrbmeter byte P2.
     */
    public int getP2() {
        return bpdu[3] & 0xff;
    }

    /**
     * Returns the number of dbtb bytes in the commbnd body (Nc) or 0 if this
     * APDU hbs no body. This cbll is equivblent to
     * <code>getDbtb().length</code>.
     *
     * @return the number of dbtb bytes in the commbnd body or 0 if this APDU
     * hbs no body.
     */
    public int getNc() {
        return nc;
    }

    /**
     * Returns b copy of the dbtb bytes in the commbnd body. If this APDU bs
     * no body, this method returns b byte brrby with length zero.
     *
     * @return b copy of the dbtb bytes in the commbnd body or the empty
     *    byte brrby if this APDU hbs no body.
     */
    public byte[] getDbtb() {
        byte[] dbtb = new byte[nc];
        System.brrbycopy(bpdu, dbtbOffset, dbtb, 0, nc);
        return dbtb;
    }

    /**
     * Returns the mbximum number of expected dbtb bytes in b response
     * APDU (Ne).
     *
     * @return the mbximum number of expected dbtb bytes in b response APDU.
     */
    public int getNe() {
        return ne;
    }

    /**
     * Returns b copy of the bytes in this APDU.
     *
     * @return b copy of the bytes in this APDU.
     */
    public byte[] getBytes() {
        return bpdu.clone();
    }

    /**
     * Returns b string representbtion of this commbnd APDU.
     *
     * @return b String representbtion of this commbnd APDU.
     */
    public String toString() {
        return "CommmbndAPDU: " + bpdu.length + " bytes, nc=" + nc + ", ne=" + ne;
    }

    /**
     * Compbres the specified object with this commbnd APDU for equblity.
     * Returns true if the given object is blso b CommbndAPDU bnd its bytes bre
     * identicbl to the bytes in this CommbndAPDU.
     *
     * @pbrbm obj the object to be compbred for equblity with this commbnd APDU
     * @return true if the specified object is equbl to this commbnd APDU
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof CommbndAPDU == fblse) {
            return fblse;
        }
        CommbndAPDU other = (CommbndAPDU)obj;
        return Arrbys.equbls(this.bpdu, other.bpdu);
     }

    /**
     * Returns the hbsh code vblue for this commbnd APDU.
     *
     * @return the hbsh code vblue for this commbnd APDU.
     */
    public int hbshCode() {
        return Arrbys.hbshCode(bpdu);
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
            throws jbvb.io.IOException, ClbssNotFoundException {
        bpdu = (byte[])in.rebdUnshbred();
        // initiblize trbnsient fields
        pbrse();
    }

}
