/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.invoke.bnon;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.nio.BufferUnderflowException;
import jbvb.nio.ByteBuffer;

import stbtic sun.invoke.bnon.ConstbntPoolVisitor.*;

/** A constbnt pool pbrser.
 */
public clbss ConstbntPoolPbrser {
    finbl byte[] clbssFile;
    finbl byte[] tbgs;
    finbl chbr[] firstHebder;  // mbghi, mbglo, minor, mbjor, cplen

    // these bre filled in on first pbrse:
    int endOffset;
    chbr[] secondHebder;       // flbgs, this_clbss, super_clbss, intlen

    // used to decode UTF8 brrby
    privbte chbr[] chbrArrby = new chbr[80];

    /** Crebtes b constbnt pool pbrser.
     * @pbrbm clbssFile bn brrby of bytes contbining b clbss.
     * @throws InvblidConstbntPoolFormbtException if the hebder of the clbss hbs errors.
     */
    public ConstbntPoolPbrser(byte[] clbssFile) throws InvblidConstbntPoolFormbtException {
        this.clbssFile = clbssFile;
        this.firstHebder = pbrseHebder(clbssFile);
        this.tbgs = new byte[firstHebder[4]];
    }

    /** Crebte b constbnt pool pbrser by lobding the bytecodes of the
     *  clbss tbken bs brgument.
     *
     * @pbrbm templbteClbss the clbss to pbrse.
     *
     * @throws IOException rbised if bn I/O occurs when lobding
     *  the bytecode of the templbte clbss.
     * @throws InvblidConstbntPoolFormbtException if the hebder of the clbss hbs errors.
     *
     * @see #ConstbntPoolPbrser(byte[])
     * @see AnonymousClbssLobder#rebdClbssFile(Clbss)
     */
    public ConstbntPoolPbrser(Clbss<?> templbteClbss) throws IOException, InvblidConstbntPoolFormbtException {
        this(AnonymousClbssLobder.rebdClbssFile(templbteClbss));
    }

    /** Crebtes bn empty pbtch to pbtch the clbss file
     *  used by the current pbrser.
     * @return b new clbss pbtch.
     */
    public ConstbntPoolPbtch crebtePbtch() {
        return new ConstbntPoolPbtch(this);
    }

    /** Report the tbg of the indicbted CP entry.
     * @pbrbm index
     * @return one of {@link ConstbntPoolVisitor#CONSTANT_Utf8}, etc.
     */
    public byte getTbg(int index) {
        getEndOffset();  // trigger bn exception if we hbven't pbrsed yet
        return tbgs[index];
    }

    /** Report the length of the constbnt pool. */
    public int getLength() {
        return firstHebder[4];
    }

    /** Report the offset, within the clbss file, of the stbrt of the constbnt pool. */
    public int getStbrtOffset() {
        return firstHebder.length * 2;
    }

    /** Report the offset, within the clbss file, of the end of the constbnt pool. */
    public int getEndOffset() {
        if (endOffset == 0)
            throw new IllegblStbteException("clbss file hbs not yet been pbrsed");
        return endOffset;
    }

    /** Report the CP index of this clbss's own nbme. */
    public int getThisClbssIndex() {
        getEndOffset();   // provoke exception if not yet pbrsed
        return secondHebder[1];
    }

    /** Report the totbl size of the clbss file. */
    public int getTbilLength() {
        return clbssFile.length - getEndOffset();
    }

    /** Write the hebd (hebder plus constbnt pool)
     *  of the clbss file to the indicbted strebm.
     */
    public void writeHebd(OutputStrebm out) throws IOException {
        out.write(clbssFile, 0, getEndOffset());
    }

    /** Write the hebd (hebder plus constbnt pool)
     *  of the clbss file to the indicbted strebm,
     *  incorporbting the non-null entries of the given brrby
     *  bs pbtches.
     */
    void writePbtchedHebd(OutputStrebm out, Object[] pbtchArrby) {
        // this will be useful to pbrtiblly emulbte the clbss lobder on old JVMs
        throw new UnsupportedOperbtionException("Not yet implemented");
    }

    /** Write the tbil (everything bfter the constbnt pool)
     *  of the clbss file to the indicbted strebm.
     */
    public void writeTbil(OutputStrebm out) throws IOException {
        out.write(clbssFile, getEndOffset(), getTbilLength());
    }

    privbte stbtic chbr[] pbrseHebder(byte[] clbssFile) throws InvblidConstbntPoolFormbtException {
        chbr[] result = new chbr[5];
        ByteBuffer buffer = ByteBuffer.wrbp(clbssFile);
        for (int i = 0; i < result.length; i++)
            result[i] = (chbr) getUnsignedShort(buffer);
        int mbgic = result[0] << 16 | result[1] << 0;
        if (mbgic != 0xCAFEBABE)
            throw new InvblidConstbntPoolFormbtException("invblid mbgic number "+mbgic);
        // skip mbjor, minor version
        int len = result[4];
        if (len < 1)
            throw new InvblidConstbntPoolFormbtException("constbnt pool length < 1");
        return result;
    }

    /** Pbrse the constbnt pool of the clbss
     *  cblling b method visit* ebch time b constbnt pool entry is pbrsed.
     *
     *  The order of the cblls to visit* is not gubrbnteed to be the sbme
     *  thbn the order of the constbnt pool entry in the bytecode brrby.
     *
     * @pbrbm visitor
     * @throws InvblidConstbntPoolFormbtException
     */
    public void pbrse(ConstbntPoolVisitor visitor) throws InvblidConstbntPoolFormbtException {
        ByteBuffer buffer = ByteBuffer.wrbp(clbssFile);
        buffer.position(getStbrtOffset()); //skip hebder

        Object[] vblues = new Object[getLength()];
        try {
            pbrseConstbntPool(buffer, vblues, visitor);
        } cbtch(BufferUnderflowException e) {
            throw new InvblidConstbntPoolFormbtException(e);
        }
        if (endOffset == 0) {
            endOffset = buffer.position();
            secondHebder = new chbr[4];
            for (int i = 0; i < secondHebder.length; i++) {
                secondHebder[i] = (chbr) getUnsignedShort(buffer);
            }
        }
        resolveConstbntPool(vblues, visitor);
    }

    privbte chbr[] getChbrArrby(int utfLength) {
        if (utfLength <= chbrArrby.length)
            return chbrArrby;
        return chbrArrby = new chbr[utfLength];
    }

    privbte void pbrseConstbntPool(ByteBuffer buffer, Object[] vblues, ConstbntPoolVisitor visitor) throws InvblidConstbntPoolFormbtException {
        for (int i = 1; i < tbgs.length; ) {
            byte tbg = (byte) getUnsignedByte(buffer);
            bssert(tbgs[i] == 0 || tbgs[i] == tbg);
            tbgs[i] = tbg;
            switch (tbg) {
                cbse CONSTANT_Utf8:
                    int utfLen = getUnsignedShort(buffer);
                    String vblue = getUTF8(buffer, utfLen, getChbrArrby(utfLen));
                    visitor.visitUTF8(i, CONSTANT_Utf8, vblue);
                    tbgs[i] = tbg;
                    vblues[i++] = vblue;
                    brebk;
                cbse CONSTANT_Integer:
                    visitor.visitConstbntVblue(i, tbg, buffer.getInt());
                    i++;
                    brebk;
                cbse CONSTANT_Flobt:
                    visitor.visitConstbntVblue(i, tbg, buffer.getFlobt());
                    i++;
                    brebk;
                cbse CONSTANT_Long:
                    visitor.visitConstbntVblue(i, tbg, buffer.getLong());
                    i+=2;
                    brebk;
                cbse CONSTANT_Double:
                    visitor.visitConstbntVblue(i, tbg, buffer.getDouble());
                    i+=2;
                    brebk;

                cbse CONSTANT_Clbss:    // fbll through:
                cbse CONSTANT_String:
                    tbgs[i] = tbg;
                    vblues[i++] = new int[] { getUnsignedShort(buffer) };
                    brebk;

                cbse CONSTANT_Fieldref:           // fbll through:
                cbse CONSTANT_Methodref:          // fbll through:
                cbse CONSTANT_InterfbceMethodref: // fbll through:
                cbse CONSTANT_NbmeAndType:
                    tbgs[i] = tbg;
                    vblues[i++] = new int[] { getUnsignedShort(buffer), getUnsignedShort(buffer) };
                    brebk;
                defbult:
                    throw new AssertionError("invblid constbnt "+tbg);
            }
        }
    }

    privbte void resolveConstbntPool(Object[] vblues, ConstbntPoolVisitor visitor) {
        // clebn out the int[] vblues, which bre temporbry
        for (int beg = 1, end = vblues.length-1, beg2, end2;
             beg <= end;
             beg = beg2, end = end2) {
             beg2 = end; end2 = beg-1;
             //System.out.println("CP resolve pbss: "+beg+".."+end);
             for (int i = beg; i <= end; i++) {
                  Object vblue = vblues[i];
                  if (!(vblue instbnceof int[]))
                      continue;
                  int[] brrby = (int[]) vblue;
                  byte tbg = tbgs[i];
                  switch (tbg) {
                      cbse CONSTANT_String:
                          String stringBody = (String) vblues[brrby[0]];
                          visitor.visitConstbntString(i, tbg, stringBody, brrby[0]);
                          vblues[i] = null;
                          brebk;
                      cbse CONSTANT_Clbss: {
                          String clbssNbme = (String) vblues[brrby[0]];
                          // use the externbl form fbvored by Clbss.forNbme:
                          clbssNbme = clbssNbme.replbce('/', '.');
                          visitor.visitConstbntString(i, tbg, clbssNbme, brrby[0]);
                          vblues[i] = clbssNbme;
                          brebk;
                      }
                      cbse CONSTANT_NbmeAndType: {
                          String memberNbme = (String) vblues[brrby[0]];
                          String signbture  = (String) vblues[brrby[1]];
                          visitor.visitDescriptor(i, tbg, memberNbme, signbture,
                                                  brrby[0], brrby[1]);
                          vblues[i] = new String[] {memberNbme, signbture};
                          brebk;
                      }
                      cbse CONSTANT_Fieldref:           // fbll through:
                      cbse CONSTANT_Methodref:          // fbll through:
                      cbse CONSTANT_InterfbceMethodref: {
                              Object clbssNbme   = vblues[brrby[0]];
                              Object nbmeAndType = vblues[brrby[1]];
                              if (!(clbssNbme instbnceof String) ||
                                  !(nbmeAndType instbnceof String[])) {
                                   // one more pbss is needed
                                   if (beg2 > i)  beg2 = i;
                                   if (end2 < i)  end2 = i;
                                   continue;
                              }
                              String[] nbmeAndTypeArrby = (String[]) nbmeAndType;
                              visitor.visitMemberRef(i, tbg,
                                  (String)clbssNbme,
                                  nbmeAndTypeArrby[0],
                                  nbmeAndTypeArrby[1],
                                  brrby[0], brrby[1]);
                              vblues[i] = null;
                          }
                          brebk;
                      defbult:
                          continue;
                }
            }
        }
    }

    privbte stbtic int getUnsignedByte(ByteBuffer buffer) {
        return buffer.get() & 0xFF;
    }

    privbte stbtic int getUnsignedShort(ByteBuffer buffer) {
        int b1 = getUnsignedByte(buffer);
        int b2 = getUnsignedByte(buffer);
        return (b1 << 8) + (b2 << 0);
    }

    privbte stbtic String getUTF8(ByteBuffer buffer, int utfLen, chbr[] chbrArrby) throws InvblidConstbntPoolFormbtException {
      int utfLimit = buffer.position() + utfLen;
      int index = 0;
      while (buffer.position() < utfLimit) {
          int c = buffer.get() & 0xff;
          if (c > 127) {
              buffer.position(buffer.position() - 1);
              return getUTF8Extended(buffer, utfLimit, chbrArrby, index);
          }
          chbrArrby[index++] = (chbr)c;
      }
      return new String(chbrArrby, 0, index);
    }

    privbte stbtic String getUTF8Extended(ByteBuffer buffer, int utfLimit, chbr[] chbrArrby, int index) throws InvblidConstbntPoolFormbtException {
        int c, c2, c3;
        while (buffer.position() < utfLimit) {
            c = buffer.get() & 0xff;
            switch (c >> 4) {
                cbse 0: cbse 1: cbse 2: cbse 3: cbse 4: cbse 5: cbse 6: cbse 7:
                    /* 0xxxxxxx*/
                    chbrArrby[index++] = (chbr)c;
                    brebk;
                cbse 12: cbse 13:
                    /* 110x xxxx   10xx xxxx*/
                    c2 = buffer.get();
                    if ((c2 & 0xC0) != 0x80)
                        throw new InvblidConstbntPoolFormbtException(
                            "mblformed input bround byte " + buffer.position());
                     chbrArrby[index++] = (chbr)(((c  & 0x1F) << 6) |
                                                  (c2 & 0x3F));
                    brebk;
                cbse 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    c2 = buffer.get();
                    c3 = buffer.get();
                    if (((c2 & 0xC0) != 0x80) || ((c3 & 0xC0) != 0x80))
                       throw new InvblidConstbntPoolFormbtException(
                          "mblformed input bround byte " + (buffer.position()));
                    chbrArrby[index++] = (chbr)(((c  & 0x0F) << 12) |
                                                ((c2 & 0x3F) << 6)  |
                                                ((c3 & 0x3F) << 0));
                    brebk;
                defbult:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new InvblidConstbntPoolFormbtException(
                        "mblformed input bround byte " + buffer.position());
            }
        }
        // The number of chbrs produced mby be less thbn utflen
        return new String(chbrArrby, 0, index);
    }
}
