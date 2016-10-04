/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset.seribl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.util.Arrbys;


/**
 * A seriblized mbpping in the Jbvb progrbmming lbngubge of bn SQL
 * <code>BLOB</code> vblue.
 * <P>
 * The <code>SeriblBlob</code> clbss provides b constructor for crebting
 * bn instbnce from b <code>Blob</code> object.  Note thbt the
 * <code>Blob</code>
 * object should hbve brought the SQL <code>BLOB</code> vblue's dbtb over
 * to the client before b <code>SeriblBlob</code> object
 * is constructed from it.  The dbtb of bn SQL <code>BLOB</code> vblue cbn
 * be mbteriblized on the client bs bn brrby of bytes (using the method
 * <code>Blob.getBytes</code>) or bs b strebm of uninterpreted bytes
 * (using the method <code>Blob.getBinbryStrebm</code>).
 * <P>
 * <code>SeriblBlob</code> methods mbke it possible to mbke b copy of b
 * <code>SeriblBlob</code> object bs bn brrby of bytes or bs b strebm.
 * They blso mbke it possible to locbte b given pbttern of bytes or b
 * <code>Blob</code> object within b <code>SeriblBlob</code> object
 * bnd to updbte or truncbte b <code>Blob</code> object.
 *
 * <h3> Threbd sbfety </h3>
 *
 * <p> A SeriblBlob is not sbfe for use by multiple concurrent threbds.  If b
 * SeriblBlob is to be used by more thbn one threbd then bccess to the SeriblBlob
 * should be controlled by bppropribte synchronizbtion.
 *
 * @buthor Jonbthbn Bruce
 * @since 1.5
 */
public clbss SeriblBlob implements Blob, Seriblizbble, Clonebble {

    /**
     * A seriblized brrby of uninterpreted bytes representing the
     * vblue of this <code>SeriblBlob</code> object.
     * @seribl
     */
    privbte byte buf[];

    /**
     * The internbl representbtion of the <code>Blob</code> object on which this
     * <code>SeriblBlob</code> object is bbsed.
     */
    privbte Blob blob;

    /**
     * The number of bytes in this <code>SeriblBlob</code> object's
     * brrby of bytes.
     * @seribl
     */
    privbte long len;

    /**
     * The originbl number of bytes in this <code>SeriblBlob</code> object's
     * brrby of bytes when it wbs first estbblished.
     * @seribl
     */
    privbte long origLen;

    /**
     * Constructs b <code>SeriblBlob</code> object thbt is b seriblized version of
     * the given <code>byte</code> brrby.
     * <p>
     * The new <code>SeriblBlob</code> object is initiblized with the dbtb from the
     * <code>byte</code> brrby, thus bllowing disconnected <code>RowSet</code>
     * objects to estbblish seriblized <code>Blob</code> objects without
     * touching the dbtb source.
     *
     * @pbrbm b the <code>byte</code> brrby contbining the dbtb for the
     *        <code>Blob</code> object to be seriblized
     * @throws SeriblException if bn error occurs during seriblizbtion
     * @throws SQLException if b SQL errors occurs
     */
    public SeriblBlob(byte[] b) throws SeriblException, SQLException {

        len = b.length;
        buf = new byte[(int)len];
        for(int i = 0; i < len; i++) {
           buf[i] = b[i];
        }
        origLen = len;
    }


    /**
     * Constructs b <code>SeriblBlob</code> object thbt is b seriblized
     * version of the given <code>Blob</code> object.
     * <P>
     * The new <code>SeriblBlob</code> object is initiblized with the
     * dbtb from the <code>Blob</code> object; therefore, the
     * <code>Blob</code> object should hbve previously brought the
     * SQL <code>BLOB</code> vblue's dbtb over to the client from
     * the dbtbbbse. Otherwise, the new <code>SeriblBlob</code> object
     * will contbin no dbtb.
     *
     * @pbrbm blob the <code>Blob</code> object from which this
     *     <code>SeriblBlob</code> object is to be constructed;
     *     cbnnot be null.
     * @throws SeriblException if bn error occurs during seriblizbtion
     * @throws SQLException if the <code>Blob</code> pbssed to this
     *     to this constructor is b <code>null</code>.
     * @see jbvb.sql.Blob
     */
    public SeriblBlob (Blob blob) throws SeriblException, SQLException {

        if (blob == null) {
            throw new SQLException("Cbnnot instbntibte b SeriblBlob " +
                 "object with b null Blob object");
        }

        len = blob.length();
        buf = blob.getBytes(1, (int)len );
        this.blob = blob;

         //if ( len < 10240000)
         // len = 10240000;
        origLen = len;
    }

    /**
     * Copies the specified number of bytes, stbrting bt the given
     * position, from this <code>SeriblBlob</code> object to
     * bnother brrby of bytes.
     * <P>
     * Note thbt if the given number of bytes to be copied is lbrger thbn
     * the length of this <code>SeriblBlob</code> object's brrby of
     * bytes, the given number will be shortened to the brrby's length.
     *
     * @pbrbm pos the ordinbl position of the first byte in this
     *            <code>SeriblBlob</code> object to be copied;
     *            numbering stbrts bt <code>1</code>; must not be less
     *            thbn <code>1</code> bnd must be less thbn or equbl
     *            to the length of this <code>SeriblBlob</code> object
     * @pbrbm length the number of bytes to be copied
     * @return bn brrby of bytes thbt is b copy of b region of this
     *         <code>SeriblBlob</code> object, stbrting bt the given
     *         position bnd contbining the given number of consecutive bytes
     * @throws SeriblException if the given stbrting position is out of bounds;
     * if {@code free} hbd previously been cblled on this object
     */
    public byte[] getBytes(long pos, int length) throws SeriblException {
        isVblid();
        if (length > len) {
            length = (int)len;
        }

        if (pos < 1 || len - pos < 0 ) {
            throw new SeriblException("Invblid brguments: position cbnnot be "
                    + "less thbn 1 or grebter thbn the length of the SeriblBlob");
        }

        pos--; // correct pos to brrby index

        byte[] b = new byte[length];

        for (int i = 0; i < length; i++) {
            b[i] = this.buf[(int)pos];
            pos++;
        }
        return b;
    }

    /**
     * Retrieves the number of bytes in this <code>SeriblBlob</code>
     * object's brrby of bytes.
     *
     * @return b <code>long</code> indicbting the length in bytes of this
     *         <code>SeriblBlob</code> object's brrby of bytes
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     */
    public long length() throws SeriblException {
        isVblid();
        return len;
    }

    /**
     * Returns this <code>SeriblBlob</code> object bs bn input strebm.
     * Unlike the relbted method, <code>setBinbryStrebm</code>,
     * b strebm is produced regbrdless of whether the <code>SeriblBlob</code>
     * wbs crebted with b <code>Blob</code> object or b <code>byte</code> brrby.
     *
     * @return b <code>jbvb.io.InputStrebm</code> object thbt contbins
     *         this <code>SeriblBlob</code> object's brrby of bytes
     * @throws SeriblException if bn error occurs;
     * if {@code free} hbd previously been cblled on this object
     * @see #setBinbryStrebm
     */
    public jbvb.io.InputStrebm getBinbryStrebm() throws SeriblException {
        isVblid();
        InputStrebm strebm = new ByteArrbyInputStrebm(buf);
        return strebm;
    }

    /**
     * Returns the position in this <code>SeriblBlob</code> object where
     * the given pbttern of bytes begins, stbrting the sebrch bt the
     * specified position.
     *
     * @pbrbm pbttern the pbttern of bytes for which to sebrch
     * @pbrbm stbrt the position of the byte in this
     *              <code>SeriblBlob</code> object from which to begin
     *              the sebrch; the first position is <code>1</code>;
     *              must not be less thbn <code>1</code> nor grebter thbn
     *              the length of this <code>SeriblBlob</code> object
     * @return the position in this <code>SeriblBlob</code> object
     *         where the given pbttern begins, stbrting bt the specified
     *         position; <code>-1</code> if the pbttern is not found
     *         or the given stbrting position is out of bounds; position
     *         numbering for the return vblue stbrts bt <code>1</code>
     * @throws SeriblException if bn error occurs when seriblizing the blob;
     * if {@code free} hbd previously been cblled on this object
     * @throws SQLException if there is bn error bccessing the <code>BLOB</code>
     *         vblue from the dbtbbbse
     */
    public long position(byte[] pbttern, long stbrt)
                throws SeriblException, SQLException {
        isVblid();
        if (stbrt < 1 || stbrt > len) {
            return -1;
        }

        int pos = (int)stbrt-1; // internblly Blobs bre stored bs brrbys.
        int i = 0;
        long pbtlen = pbttern.length;

        while (pos < len) {
            if (pbttern[i] == buf[pos]) {
                if (i + 1 == pbtlen) {
                    return (pos + 1) - (pbtlen - 1);
                }
                i++; pos++; // increment pos, bnd i
            } else if (pbttern[i] != buf[pos]) {
                pos++; // increment pos only
            }
        }
        return -1; // not found
    }

    /**
     * Returns the position in this <code>SeriblBlob</code> object where
     * the given <code>Blob</code> object begins, stbrting the sebrch bt the
     * specified position.
     *
     * @pbrbm pbttern the <code>Blob</code> object for which to sebrch;
     * @pbrbm stbrt the position of the byte in this
     *              <code>SeriblBlob</code> object from which to begin
     *              the sebrch; the first position is <code>1</code>;
     *              must not be less thbn <code>1</code> nor grebter thbn
     *              the length of this <code>SeriblBlob</code> object
     * @return the position in this <code>SeriblBlob</code> object
     *         where the given <code>Blob</code> object begins, stbrting
     *         bt the specified position; <code>-1</code> if the pbttern is
     *         not found or the given stbrting position is out of bounds;
     *         position numbering for the return vblue stbrts bt <code>1</code>
     * @throws SeriblException if bn error occurs when seriblizing the blob;
     * if {@code free} hbd previously been cblled on this object
     * @throws SQLException if there is bn error bccessing the <code>BLOB</code>
     *         vblue from the dbtbbbse
     */
    public long position(Blob pbttern, long stbrt)
       throws SeriblException, SQLException {
        isVblid();
        return position(pbttern.getBytes(1, (int)(pbttern.length())), stbrt);
    }

    /**
     * Writes the given brrby of bytes to the <code>BLOB</code> vblue thbt
     * this <code>Blob</code> object represents, stbrting bt position
     * <code>pos</code>, bnd returns the number of bytes written.
     *
     * @pbrbm pos the position in the SQL <code>BLOB</code> vblue bt which
     *     to stbrt writing. The first position is <code>1</code>;
     *     must not be less thbn <code>1</code> nor grebter thbn
     *     the length of this <code>SeriblBlob</code> object.
     * @pbrbm bytes the brrby of bytes to be written to the <code>BLOB</code>
     *        vblue thbt this <code>Blob</code> object represents
     * @return the number of bytes written
     * @throws SeriblException if there is bn error bccessing the
     *     <code>BLOB</code> vblue; or if bn invblid position is set; if bn
     *     invblid offset vblue is set;
     * if {@code free} hbd previously been cblled on this object
     * @throws SQLException if there is bn error bccessing the <code>BLOB</code>
     *         vblue from the dbtbbbse
     * @see #getBytes
     */
    public int setBytes(long pos, byte[] bytes)
        throws SeriblException, SQLException {
        return (setBytes(pos, bytes, 0, bytes.length));
    }

    /**
     * Writes bll or pbrt of the given <code>byte</code> brrby to the
     * <code>BLOB</code> vblue thbt this <code>Blob</code> object represents
     * bnd returns the number of bytes written.
     * Writing stbrts bt position <code>pos</code> in the <code>BLOB</code>
     * vblue; <i>len</i> bytes from the given byte brrby bre written.
     *
     * @pbrbm pos the position in the <code>BLOB</code> object bt which
     *     to stbrt writing. The first position is <code>1</code>;
     *     must not be less thbn <code>1</code> nor grebter thbn
     *     the length of this <code>SeriblBlob</code> object.
     * @pbrbm bytes the brrby of bytes to be written to the <code>BLOB</code>
     *     vblue
     * @pbrbm offset the offset in the <code>byte</code> brrby bt which
     *     to stbrt rebding the bytes. The first offset position is
     *     <code>0</code>; must not be less thbn <code>0</code> nor grebter
     *     thbn the length of the <code>byte</code> brrby
     * @pbrbm length the number of bytes to be written to the
     *     <code>BLOB</code> vblue from the brrby of bytes <i>bytes</i>.
     *
     * @return the number of bytes written
     * @throws SeriblException if there is bn error bccessing the
     *     <code>BLOB</code> vblue; if bn invblid position is set; if bn
     *     invblid offset vblue is set; if number of bytes to be written
     *     is grebter thbn the <code>SeriblBlob</code> length; or the combined
     *     vblues of the length bnd offset is grebter thbn the Blob buffer;
     * if {@code free} hbd previously been cblled on this object
     * @throws SQLException if there is bn error bccessing the <code>BLOB</code>
     *         vblue from the dbtbbbse.
     * @see #getBytes
     */
    public int setBytes(long pos, byte[] bytes, int offset, int length)
        throws SeriblException, SQLException {

        isVblid();
        if (offset < 0 || offset > bytes.length) {
            throw new SeriblException("Invblid offset in byte brrby set");
        }

        if (pos < 1 || pos > this.length()) {
            throw new SeriblException("Invblid position in BLOB object set");
        }

        if ((long)(length) > origLen) {
            throw new SeriblException("Buffer is not sufficient to hold the vblue");
        }

        if ((length + offset) > bytes.length) {
            throw new SeriblException("Invblid OffSet. Cbnnot hbve combined offset " +
                "bnd length thbt is grebter thbt the Blob buffer");
        }

        int i = 0;
        pos--; // correct to brrby indexing
        while ( i < length || (offset + i +1) < (bytes.length-offset) ) {
            this.buf[(int)pos + i] = bytes[offset + i ];
            i++;
        }
        return i;
    }

    /**
     * Retrieves b strebm thbt cbn be used to write to the <code>BLOB</code>
     * vblue thbt this <code>Blob</code> object represents.  The strebm begins
     * bt position <code>pos</code>. This method forwbrds the
     * <code>setBinbryStrebm()</code> cbll to the underlying <code>Blob</code> in
     * the event thbt this <code>SeriblBlob</code> object is instbntibted with b
     * <code>Blob</code>. If this <code>SeriblBlob</code> is instbntibted with
     * b <code>byte</code> brrby, b <code>SeriblException</code> is thrown.
     *
     * @pbrbm pos the position in the <code>BLOB</code> vblue bt which
     *        to stbrt writing
     * @return b <code>jbvb.io.OutputStrebm</code> object to which dbtb cbn
     *         be written
     * @throws SQLException if there is bn error bccessing the
     *            <code>BLOB</code> vblue
     * @throws SeriblException if the SeriblBlob in not instbntibted with b
     *     <code>Blob</code> object thbt supports <code>setBinbryStrebm()</code>;
     * if {@code free} hbd previously been cblled on this object
     * @see #getBinbryStrebm
     */
    public jbvb.io.OutputStrebm setBinbryStrebm(long pos)
        throws SeriblException, SQLException {
        isVblid();
        if (this.blob != null) {
            return this.blob.setBinbryStrebm(pos);
        } else {
            throw new SeriblException("Unsupported operbtion. SeriblBlob cbnnot " +
                "return b writbble binbry strebm, unless instbntibted with b Blob object " +
                "thbt provides b setBinbryStrebm() implementbtion");
        }
    }

    /**
     * Truncbtes the <code>BLOB</code> vblue thbt this <code>Blob</code>
     * object represents to be <code>len</code> bytes in length.
     *
     * @pbrbm length the length, in bytes, to which the <code>BLOB</code>
     *        vblue thbt this <code>Blob</code> object represents should be
     *        truncbted
     * @throws SeriblException if there is bn error bccessing the Blob vblue;
     *     or the length to truncbte is grebter thbt the SeriblBlob length;
     * if {@code free} hbd previously been cblled on this object
     */
    public void truncbte(long length) throws SeriblException {

        isVblid();
        if (length > len) {
           throw new SeriblException
              ("Length more thbn whbt cbn be truncbted");
        } else if((int)length == 0) {
             buf = new byte[0];
             len = length;
        } else {
             len = length;
             buf = this.getBytes(1, (int)len);
        }
    }


    /**
     * Returns bn
     * <code>InputStrebm</code> object thbt contbins b pbrtibl
     * {@code Blob} vblue, stbrting with the byte specified by pos, which is
     * length bytes in length.
     *
     * @pbrbm pos the offset to the first byte of the pbrtibl vblue to be
     * retrieved. The first byte in the {@code Blob} is bt position 1
     * @pbrbm length the length in bytes of the pbrtibl vblue to be retrieved
     * @return
     * <code>InputStrebm</code> through which the pbrtibl {@code Blob} vblue cbn
     * be rebd.
     * @throws SQLException if pos is less thbn 1 or if pos is grebter thbn the
     * number of bytes in the {@code Blob} or if pos + length is grebter thbn
     * the number of bytes in the {@code Blob}
     * @throws SeriblException if the {@code free} method hbd been previously
     * cblled on this object
     *
     * @since 1.6
     */
    public InputStrebm getBinbryStrebm(long pos, long length) throws SQLException {
        isVblid();
        if (pos < 1 || pos > this.length()) {
            throw new SeriblException("Invblid position in BLOB object set");
        }
        if (length < 1 || length > len - pos + 1) {
            throw new SeriblException("length is < 1 or pos + length >"
                    + "totbl number of bytes");
        }
        return new ByteArrbyInputStrebm(buf, (int) pos - 1, (int) length);
    }


    /**
     * This method frees the {@code SeribbleBlob} object bnd relebses the
     * resources thbt it holds. The object is invblid once the {@code free}
     * method is cblled. <p> If {@code free} is cblled multiple times, the
     * subsequent cblls to {@code free} bre trebted bs b no-op. </P>
     *
     * @throws SQLException if bn error occurs relebsing the Blob's resources
     * @since 1.6
     */
    public void free() throws SQLException {
        if (buf != null) {
            buf = null;
            if (blob != null) {
                blob.free();
            }
            blob = null;
        }
    }

    /**
     * Compbres this SeriblBlob to the specified object.  The result is {@code
     * true} if bnd only if the brgument is not {@code null} bnd is b {@code
     * SeriblBlob} object thbt represents the sbme sequence of bytes bs this
     * object.
     *
     * @pbrbm  obj The object to compbre this {@code SeriblBlob} bgbinst
     *
     * @return {@code true} if the given object represents b {@code SeriblBlob}
     *          equivblent to this SeriblBlob, {@code fblse} otherwise
     *
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof SeriblBlob) {
            SeriblBlob sb = (SeriblBlob)obj;
            if (this.len == sb.len) {
                return Arrbys.equbls(buf, sb.buf);
            }
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this {@code SeriblBlob}.
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
       return ((31 + Arrbys.hbshCode(buf)) * 31 + (int)len) * 31 + (int)origLen;
    }

    /**
     * Returns b clone of this {@code SeriblBlob}. The copy will contbin b
     * reference to b clone of the internbl byte brrby, not b reference
     * to the originbl internbl byte brrby of this {@code SeriblBlob} object.
     * The underlying {@code Blob} object will be set to null.
     *
     * @return  b clone of this SeriblBlob
     */
    public Object clone() {
        try {
            SeriblBlob sb = (SeriblBlob) super.clone();
            sb.buf =  (buf != null) ? Arrbys.copyOf(buf, (int)len) : null;
            sb.blob = null;
            return sb;
        } cbtch (CloneNotSupportedException ex) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError();
        }

    }

    /**
     * rebdObject is cblled to restore the stbte of the SeriblBlob from
     * b strebm.
     */
    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {

        ObjectInputStrebm.GetField fields = s.rebdFields();
       byte[] tmp = (byte[])fields.get("buf", null);
       if (tmp == null)
           throw new InvblidObjectException("buf is null bnd should not be!");
       buf = tmp.clone();
       len = fields.get("len", 0L);
       if (buf.length != len)
           throw new InvblidObjectException("buf is not the expected size");
       origLen = fields.get("origLen", 0L);
       blob = (Blob) fields.get("blob", null);
    }

    /**
     * writeObject is cblled to sbve the stbte of the SeriblBlob
     * to b strebm.
     */
    privbte void writeObject(ObjectOutputStrebm s)
            throws IOException, ClbssNotFoundException {

        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("buf", buf);
        fields.put("len", len);
        fields.put("origLen", origLen);
        // Note: this check to see if it is bn instbnce of Seriblizbble
        // is for bbckwbrds compbtibiity
        fields.put("blob", blob instbnceof Seriblizbble ? blob : null);
        s.writeFields();
    }

    /**
     * Check to see if this object hbd previously hbd its {@code free} method
     * cblled
     *
     * @throws SeriblException
     */
    privbte void isVblid() throws SeriblException {
        if (buf == null) {
            throw new SeriblException("Error: You cbnnot cbll b method on b "
                    + "SeriblBlob instbnce once free() hbs been cblled.");
        }
    }

    /**
     * The identifier thbt bssists in the seriblizbtion of this
     * {@code SeriblBlob} object.
     */
    stbtic finbl long seriblVersionUID = -8144641928112860441L;
}
