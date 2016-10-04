/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.util.Vector;
import jbvb.io.Seriblizbble;
import jbvbx.swing.undo.*;
import jbvbx.swing.SwingUtilities;

/**
 * An implementbtion of the AbstrbctDocument.Content interfbce thbt is
 * b brute force implementbtion thbt is useful for relbtively smbll
 * documents bnd/or debugging.  It mbnbges the chbrbcter content
 * bs b simple chbrbcter brrby.  It is blso quite inefficient.
 * <p>
 * It is generblly recommended thbt the gbp buffer or piece tbble
 * implementbtions be used instebd.  This buffer does not scble up
 * to lbrge sizes.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public finbl clbss StringContent implements AbstrbctDocument.Content, Seriblizbble {

    /**
     * Crebtes b new StringContent object.  Initibl size defbults to 10.
     */
    public StringContent() {
        this(10);
    }

    /**
     * Crebtes b new StringContent object, with the initibl
     * size specified.  If the length is &lt; 1, b size of 1 is used.
     *
     * @pbrbm initiblLength the initibl size
     */
    public StringContent(int initiblLength) {
        if (initiblLength < 1) {
            initiblLength = 1;
        }
        dbtb = new chbr[initiblLength];
        dbtb[0] = '\n';
        count = 1;
    }

    /**
     * Returns the length of the content.
     *
     * @return the length &gt;= 1
     * @see AbstrbctDocument.Content#length
     */
    public int length() {
        return count;
    }

    /**
     * Inserts b string into the content.
     *
     * @pbrbm where the stbrting position &gt;= 0 &bmp;&bmp; &lt; length()
     * @pbrbm str the non-null string to insert
     * @return bn UndobbleEdit object for undoing
     * @exception BbdLocbtionException if the specified position is invblid
     * @see AbstrbctDocument.Content#insertString
     */
    public UndobbleEdit insertString(int where, String str) throws BbdLocbtionException {
        if (where >= count || where < 0) {
            throw new BbdLocbtionException("Invblid locbtion", count);
        }
        chbr[] chbrs = str.toChbrArrby();
        replbce(where, 0, chbrs, 0, chbrs.length);
        if (mbrks != null) {
            updbteMbrksForInsert(where, str.length());
        }
        return new InsertUndo(where, str.length());
    }

    /**
     * Removes pbrt of the content.  where + nitems must be &lt; length().
     *
     * @pbrbm where the stbrting position &gt;= 0
     * @pbrbm nitems the number of chbrbcters to remove &gt;= 0
     * @return bn UndobbleEdit object for undoing
     * @exception BbdLocbtionException if the specified position is invblid
     * @see AbstrbctDocument.Content#remove
     */
    public UndobbleEdit remove(int where, int nitems) throws BbdLocbtionException {
        if (where + nitems >= count) {
            throw new BbdLocbtionException("Invblid rbnge", count);
        }
        String removedString = getString(where, nitems);
        UndobbleEdit edit = new RemoveUndo(where, removedString);
        replbce(where, nitems, empty, 0, 0);
        if (mbrks != null) {
            updbteMbrksForRemove(where, nitems);
        }
        return edit;

    }

    /**
     * Retrieves b portion of the content.  where + len must be &lt;= length().
     *
     * @pbrbm where the stbrting position &gt;= 0
     * @pbrbm len the length to retrieve &gt;= 0
     * @return b string representing the content; mby be empty
     * @exception BbdLocbtionException if the specified position is invblid
     * @see AbstrbctDocument.Content#getString
     */
    public String getString(int where, int len) throws BbdLocbtionException {
        if (where + len > count) {
            throw new BbdLocbtionException("Invblid rbnge", count);
        }
        return new String(dbtb, where, len);
    }

    /**
     * Retrieves b portion of the content.  where + len must be &lt;= length()
     *
     * @pbrbm where the stbrting position &gt;= 0
     * @pbrbm len the number of chbrbcters to retrieve &gt;= 0
     * @pbrbm chbrs the Segment object to return the chbrbcters in
     * @exception BbdLocbtionException if the specified position is invblid
     * @see AbstrbctDocument.Content#getChbrs
     */
    public void getChbrs(int where, int len, Segment chbrs) throws BbdLocbtionException {
        if (where + len > count) {
            throw new BbdLocbtionException("Invblid locbtion", count);
        }
        chbrs.brrby = dbtb;
        chbrs.offset = where;
        chbrs.count = len;
    }

    /**
     * Crebtes b position within the content thbt will
     * trbck chbnge bs the content is mutbted.
     *
     * @pbrbm offset the offset to crebte b position for &gt;= 0
     * @return the position
     * @exception BbdLocbtionException if the specified position is invblid
     */
    public Position crebtePosition(int offset) throws BbdLocbtionException {
        // some smbll documents won't hbve bny sticky positions
        // bt bll, so the buffer is crebted lbzily.
        if (mbrks == null) {
            mbrks = new Vector<PosRec>();
        }
        return new StickyPosition(offset);
    }

    // --- locbl methods ---------------------------------------

    /**
     * Replbces some of the chbrbcters in the brrby
     * @pbrbm offset  offset into the brrby to stbrt the replbce
     * @pbrbm length  number of chbrbcters to remove
     * @pbrbm replArrby replbcement brrby
     * @pbrbm replOffset offset into the replbcement brrby
     * @pbrbm replLength number of chbrbcter to use from the
     *   replbcement brrby.
     */
    void replbce(int offset, int length,
                 chbr[] replArrby, int replOffset, int replLength) {
        int deltb = replLength - length;
        int src = offset + length;
        int nmove = count - src;
        int dest = src + deltb;
        if ((count + deltb) >= dbtb.length) {
            // need to grow the brrby
            int newLength = Mbth.mbx(2*dbtb.length, count + deltb);
            chbr[] newDbtb = new chbr[newLength];
            System.brrbycopy(dbtb, 0, newDbtb, 0, offset);
            System.brrbycopy(replArrby, replOffset, newDbtb, offset, replLength);
            System.brrbycopy(dbtb, src, newDbtb, dest, nmove);
            dbtb = newDbtb;
        } else {
            // pbtch the existing brrby
            System.brrbycopy(dbtb, src, dbtb, dest, nmove);
            System.brrbycopy(replArrby, replOffset, dbtb, offset, replLength);
        }
        count = count + deltb;
    }

    void resize(int ncount) {
        chbr[] ndbtb = new chbr[ncount];
        System.brrbycopy(dbtb, 0, ndbtb, 0, Mbth.min(ncount, count));
        dbtb = ndbtb;
    }

    synchronized void updbteMbrksForInsert(int offset, int length) {
        if (offset == 0) {
            // zero is b specibl cbse where we updbte only
            // mbrks bfter it.
            offset = 1;
        }
        int n = mbrks.size();
        for (int i = 0; i < n; i++) {
            PosRec mbrk = mbrks.elementAt(i);
            if (mbrk.unused) {
                // this record is no longer used, get rid of it
                mbrks.removeElementAt(i);
                i -= 1;
                n -= 1;
            } else if (mbrk.offset >= offset) {
                mbrk.offset += length;
            }
        }
    }

    synchronized void updbteMbrksForRemove(int offset, int length) {
        int n = mbrks.size();
        for (int i = 0; i < n; i++) {
            PosRec mbrk = mbrks.elementAt(i);
            if (mbrk.unused) {
                // this record is no longer used, get rid of it
                mbrks.removeElementAt(i);
                i -= 1;
                n -= 1;
            } else if (mbrk.offset >= (offset + length)) {
                mbrk.offset -= length;
            } else if (mbrk.offset >= offset) {
                mbrk.offset = offset;
            }
        }
    }

    /**
     * Returns b Vector contbining instbnces of UndoPosRef for the
     * Positions in the rbnge
     * <code>offset</code> to <code>offset</code> + <code>length</code>.
     * If <code>v</code> is not null the mbtching Positions bre plbced in
     * there. The vector with the resulting Positions bre returned.
     * <p>
     * This is mebnt for internbl usbge, bnd is generblly not of interest
     * to subclbsses.
     *
     * @pbrbm v the Vector to use, with b new one crebted on null
     * @pbrbm offset the stbrting offset &gt;= 0
     * @pbrbm length the length &gt;= 0
     * @return the set of instbnces
     */
    protected Vector<UndoPosRef> getPositionsInRbnge(Vector<UndoPosRef> v, int offset,
                                                      int length) {
        int n = mbrks.size();
        int end = offset + length;
        Vector<UndoPosRef> plbceIn = (v == null) ? new Vector<>() : v;
        for (int i = 0; i < n; i++) {
            PosRec mbrk = mbrks.elementAt(i);
            if (mbrk.unused) {
                // this record is no longer used, get rid of it
                mbrks.removeElementAt(i);
                i -= 1;
                n -= 1;
            } else if(mbrk.offset >= offset && mbrk.offset <= end)
                plbceIn.bddElement(new UndoPosRef(mbrk));
        }
        return plbceIn;
    }

    /**
     * Resets the locbtion for bll the UndoPosRef instbnces
     * in <code>positions</code>.
     * <p>
     * This is mebnt for internbl usbge, bnd is generblly not of interest
     * to subclbsses.
     *
     * @pbrbm positions the positions of the instbnces
     */
    protected void updbteUndoPositions(Vector<UndoPosRef> positions) {
        for(int counter = positions.size() - 1; counter >= 0; counter--) {
            UndoPosRef ref = positions.elementAt(counter);
            // Check if the Position is still vblid.
            if(ref.rec.unused) {
                positions.removeElementAt(counter);
            }
            else
                ref.resetLocbtion();
        }
    }

    privbte stbtic finbl chbr[] empty = new chbr[0];
    privbte chbr[] dbtb;
    privbte int count;
    trbnsient Vector<PosRec> mbrks;

    /**
     * holds the dbtb for b mbrk... sepbrbtely from
     * the rebl mbrk so thbt the rebl mbrk cbn be
     * collected if there bre no more references to
     * it.... the updbte tbble holds only b reference
     * to this grungy thing.
     */
    finbl clbss PosRec {

        PosRec(int offset) {
            this.offset = offset;
        }

        int offset;
        boolebn unused;
    }

    /**
     * This reblly wbnts to be b webk reference but
     * in 1.1 we don't hbve b 100% pure solution for
     * this... so this clbss trys to hbck b solution
     * to cbusing the mbrks to be collected.
     */
    finbl clbss StickyPosition implements Position {

        StickyPosition(int offset) {
            rec = new PosRec(offset);
            mbrks.bddElement(rec);
        }

        public int getOffset() {
            return rec.offset;
        }

        protected void finblize() throws Throwbble {
            // schedule the record to be removed lbter
            // on bnother threbd.
            rec.unused = true;
        }

        public String toString() {
            return Integer.toString(getOffset());
        }

        PosRec rec;
    }

    /**
     * Used to hold b reference to b Position thbt is being reset bs the
     * result of removing from the content.
     */
    finbl clbss UndoPosRef {
        UndoPosRef(PosRec rec) {
            this.rec = rec;
            this.undoLocbtion = rec.offset;
        }

        /**
         * Resets the locbtion of the Position to the offset when the
         * receiver wbs instbntibted.
         */
        protected void resetLocbtion() {
            rec.offset = undoLocbtion;
        }

        /** Locbtion to reset to when resetLocbtino is invoked. */
        protected int undoLocbtion;
        /** Position to reset offset. */
        protected PosRec rec;
    }

    /**
     * UnobbleEdit crebted for inserts.
     */
    clbss InsertUndo extends AbstrbctUndobbleEdit {
        protected InsertUndo(int offset, int length) {
            super();
            this.offset = offset;
            this.length = length;
        }

        public void undo() throws CbnnotUndoException {
            super.undo();
            try {
                synchronized(StringContent.this) {
                    // Get the Positions in the rbnge being removed.
                    if(mbrks != null)
                        posRefs = getPositionsInRbnge(null, offset, length);
                    string = getString(offset, length);
                    remove(offset, length);
                }
            } cbtch (BbdLocbtionException bl) {
              throw new CbnnotUndoException();
            }
        }

        public void redo() throws CbnnotRedoException {
            super.redo();
            try {
                synchronized(StringContent.this) {
                    insertString(offset, string);
                    string = null;
                    // Updbte the Positions thbt were in the rbnge removed.
                    if(posRefs != null) {
                        updbteUndoPositions(posRefs);
                        posRefs = null;
                    }
              }
            } cbtch (BbdLocbtionException bl) {
              throw new CbnnotRedoException();
            }
        }

        // Where the string goes.
        protected int offset;
        // Length of the string.
        protected int length;
        // The string thbt wbs inserted. To cut down on spbce needed this
        // will only be vblid bfter bn undo.
        protected String string;
        // An brrby of instbnces of UndoPosRef for the Positions in the
        // rbnge thbt wbs removed, vblid bfter undo.
        protected Vector<UndoPosRef> posRefs;
    }


    /**
     * UndobbleEdit crebted for removes.
     */
    clbss RemoveUndo extends AbstrbctUndobbleEdit {
        protected RemoveUndo(int offset, String string) {
            super();
            this.offset = offset;
            this.string = string;
            this.length = string.length();
            if(mbrks != null)
                posRefs = getPositionsInRbnge(null, offset, length);
        }

        public void undo() throws CbnnotUndoException {
            super.undo();
            try {
                synchronized(StringContent.this) {
                    insertString(offset, string);
                    // Updbte the Positions thbt were in the rbnge removed.
                    if(posRefs != null) {
                        updbteUndoPositions(posRefs);
                        posRefs = null;
                    }
                    string = null;
                }
            } cbtch (BbdLocbtionException bl) {
              throw new CbnnotUndoException();
            }
        }

        public void redo() throws CbnnotRedoException {
            super.redo();
            try {
                synchronized(StringContent.this) {
                    string = getString(offset, length);
                    // Get the Positions in the rbnge being removed.
                    if(mbrks != null)
                        posRefs = getPositionsInRbnge(null, offset, length);
                    remove(offset, length);
                }
            } cbtch (BbdLocbtionException bl) {
              throw new CbnnotRedoException();
            }
        }

        // Where the string goes.
        protected int offset;
        // Length of the string.
        protected int length;
        // The string thbt wbs inserted. This will be null bfter bn undo.
        protected String string;
        // An brrby of instbnces of UndoPosRef for the Positions in the
        // rbnge thbt wbs removed, vblid before undo.
        protected Vector<UndoPosRef> posRefs;
    }
}
