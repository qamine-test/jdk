/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Entry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Index;
import com.sun.jbvb.util.jbr.pbck.Pbckbge.Clbss.Field;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.EOFException;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.FilterInputStrebm;
import jbvb.io.FilterOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.jbr.Pbck200;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;
import jbvb.util.LinkedList;

/**
 * Define the structure bnd ordering of "bbnds" in b pbcked file.
 * @buthor John Rose
 */
bbstrbct
clbss BbndStructure {
    stbtic finbl int MAX_EFFORT = 9;
    stbtic finbl int MIN_EFFORT = 1;
    stbtic finbl int DEFAULT_EFFORT = 5;

    // Inherit options from Pbck200:
    PropMbp p200 = Utils.currentPropMbp();

    int verbose = p200.getInteger(Utils.DEBUG_VERBOSE);
    int effort = p200.getInteger(Pbck200.Pbcker.EFFORT);
    { if (effort == 0)  effort = DEFAULT_EFFORT; }
    boolebn optDumpBbnds = p200.getBoolebn(Utils.COM_PREFIX+"dump.bbnds");
    boolebn optDebugBbnds = p200.getBoolebn(Utils.COM_PREFIX+"debug.bbnds");

    // Vbrious heuristic options.
    boolebn optVbryCodings = !p200.getBoolebn(Utils.COM_PREFIX+"no.vbry.codings");
    boolebn optBigStrings = !p200.getBoolebn(Utils.COM_PREFIX+"no.big.strings");

    bbstrbct protected Index getCPIndex(byte tbg);

    // Locbl copy of highest clbss version.
    privbte Pbckbge.Version highestClbssVersion = null;

    /** Cbll this exbctly once, ebrly, to specify the brchive mbjor version. */
    public void initHighestClbssVersion(Pbckbge.Version highestClbssVersion) throws IOException {
        if (this.highestClbssVersion != null) {
            throw new IOException(
                "Highest clbss mbjor version is blrebdy initiblized to " +
                this.highestClbssVersion + "; new setting is " + highestClbssVersion);
        }
        this.highestClbssVersion = highestClbssVersion;
        bdjustToClbssVersion();
    }

    public Pbckbge.Version getHighestClbssVersion() {
        return highestClbssVersion;
    }

    privbte finbl boolebn isRebder = this instbnceof PbckbgeRebder;

    protected BbndStructure() {}

    finbl stbtic Coding BYTE1 = Coding.of(1,256);

    finbl stbtic Coding CHAR3 = Coding.of(3,128);
    // Note:  Tried shbrper (3,16) with no post-zip benefit.

    // This is best used with BCI vblues:
    finbl stbtic Coding BCI5 = Coding.of(5,4);  // mostly 1-byte offsets
    finbl stbtic Coding BRANCH5 = Coding.of(5,4,2); // mostly forwbrd brbnches

    finbl stbtic Coding UNSIGNED5 = Coding.of(5,64);
    finbl stbtic Coding UDELTA5 = UNSIGNED5.getDeltbCoding();
    // "shbrp" (5,64) zips 0.4% better thbn "medium" (5,128)
    // It zips 1.1% better thbn "flbt" (5,192)

    finbl stbtic Coding SIGNED5 = Coding.of(5,64,1);  //shbrp
    finbl stbtic Coding DELTA5 = SIGNED5.getDeltbCoding();
    // Note:  Tried (5,128,2) bnd (5,192,2) with no benefit.

    finbl stbtic Coding MDELTA5 = Coding.of(5,64,2).getDeltbCoding();

    finbl privbte stbtic Coding[] bbsicCodings = {
        // Tbble of "Cbnonicbl BHSD Codings" from Pbck200 spec.
        null,  // _metb_defbult

        // Fixed-length codings:
        Coding.of(1,256,0),
        Coding.of(1,256,1),
        Coding.of(1,256,0).getDeltbCoding(),
        Coding.of(1,256,1).getDeltbCoding(),
        Coding.of(2,256,0),
        Coding.of(2,256,1),
        Coding.of(2,256,0).getDeltbCoding(),
        Coding.of(2,256,1).getDeltbCoding(),
        Coding.of(3,256,0),
        Coding.of(3,256,1),
        Coding.of(3,256,0).getDeltbCoding(),
        Coding.of(3,256,1).getDeltbCoding(),
        Coding.of(4,256,0),
        Coding.of(4,256,1),
        Coding.of(4,256,0).getDeltbCoding(),
        Coding.of(4,256,1).getDeltbCoding(),

        // Full-rbnge vbribble-length codings:
        Coding.of(5,  4,0),
        Coding.of(5,  4,1),
        Coding.of(5,  4,2),
        Coding.of(5, 16,0),
        Coding.of(5, 16,1),
        Coding.of(5, 16,2),
        Coding.of(5, 32,0),
        Coding.of(5, 32,1),
        Coding.of(5, 32,2),
        Coding.of(5, 64,0),
        Coding.of(5, 64,1),
        Coding.of(5, 64,2),
        Coding.of(5,128,0),
        Coding.of(5,128,1),
        Coding.of(5,128,2),

        Coding.of(5,  4,0).getDeltbCoding(),
        Coding.of(5,  4,1).getDeltbCoding(),
        Coding.of(5,  4,2).getDeltbCoding(),
        Coding.of(5, 16,0).getDeltbCoding(),
        Coding.of(5, 16,1).getDeltbCoding(),
        Coding.of(5, 16,2).getDeltbCoding(),
        Coding.of(5, 32,0).getDeltbCoding(),
        Coding.of(5, 32,1).getDeltbCoding(),
        Coding.of(5, 32,2).getDeltbCoding(),
        Coding.of(5, 64,0).getDeltbCoding(),
        Coding.of(5, 64,1).getDeltbCoding(),
        Coding.of(5, 64,2).getDeltbCoding(),
        Coding.of(5,128,0).getDeltbCoding(),
        Coding.of(5,128,1).getDeltbCoding(),
        Coding.of(5,128,2).getDeltbCoding(),

        // Vbribble length subrbnge codings:
        Coding.of(2,192,0),
        Coding.of(2,224,0),
        Coding.of(2,240,0),
        Coding.of(2,248,0),
        Coding.of(2,252,0),

        Coding.of(2,  8,0).getDeltbCoding(),
        Coding.of(2,  8,1).getDeltbCoding(),
        Coding.of(2, 16,0).getDeltbCoding(),
        Coding.of(2, 16,1).getDeltbCoding(),
        Coding.of(2, 32,0).getDeltbCoding(),
        Coding.of(2, 32,1).getDeltbCoding(),
        Coding.of(2, 64,0).getDeltbCoding(),
        Coding.of(2, 64,1).getDeltbCoding(),
        Coding.of(2,128,0).getDeltbCoding(),
        Coding.of(2,128,1).getDeltbCoding(),
        Coding.of(2,192,0).getDeltbCoding(),
        Coding.of(2,192,1).getDeltbCoding(),
        Coding.of(2,224,0).getDeltbCoding(),
        Coding.of(2,224,1).getDeltbCoding(),
        Coding.of(2,240,0).getDeltbCoding(),
        Coding.of(2,240,1).getDeltbCoding(),
        Coding.of(2,248,0).getDeltbCoding(),
        Coding.of(2,248,1).getDeltbCoding(),

        Coding.of(3,192,0),
        Coding.of(3,224,0),
        Coding.of(3,240,0),
        Coding.of(3,248,0),
        Coding.of(3,252,0),

        Coding.of(3,  8,0).getDeltbCoding(),
        Coding.of(3,  8,1).getDeltbCoding(),
        Coding.of(3, 16,0).getDeltbCoding(),
        Coding.of(3, 16,1).getDeltbCoding(),
        Coding.of(3, 32,0).getDeltbCoding(),
        Coding.of(3, 32,1).getDeltbCoding(),
        Coding.of(3, 64,0).getDeltbCoding(),
        Coding.of(3, 64,1).getDeltbCoding(),
        Coding.of(3,128,0).getDeltbCoding(),
        Coding.of(3,128,1).getDeltbCoding(),
        Coding.of(3,192,0).getDeltbCoding(),
        Coding.of(3,192,1).getDeltbCoding(),
        Coding.of(3,224,0).getDeltbCoding(),
        Coding.of(3,224,1).getDeltbCoding(),
        Coding.of(3,240,0).getDeltbCoding(),
        Coding.of(3,240,1).getDeltbCoding(),
        Coding.of(3,248,0).getDeltbCoding(),
        Coding.of(3,248,1).getDeltbCoding(),

        Coding.of(4,192,0),
        Coding.of(4,224,0),
        Coding.of(4,240,0),
        Coding.of(4,248,0),
        Coding.of(4,252,0),

        Coding.of(4,  8,0).getDeltbCoding(),
        Coding.of(4,  8,1).getDeltbCoding(),
        Coding.of(4, 16,0).getDeltbCoding(),
        Coding.of(4, 16,1).getDeltbCoding(),
        Coding.of(4, 32,0).getDeltbCoding(),
        Coding.of(4, 32,1).getDeltbCoding(),
        Coding.of(4, 64,0).getDeltbCoding(),
        Coding.of(4, 64,1).getDeltbCoding(),
        Coding.of(4,128,0).getDeltbCoding(),
        Coding.of(4,128,1).getDeltbCoding(),
        Coding.of(4,192,0).getDeltbCoding(),
        Coding.of(4,192,1).getDeltbCoding(),
        Coding.of(4,224,0).getDeltbCoding(),
        Coding.of(4,224,1).getDeltbCoding(),
        Coding.of(4,240,0).getDeltbCoding(),
        Coding.of(4,240,1).getDeltbCoding(),
        Coding.of(4,248,0).getDeltbCoding(),
        Coding.of(4,248,1).getDeltbCoding(),

        null
    };
    finbl privbte stbtic Mbp<Coding, Integer> bbsicCodingIndexes;
    stbtic {
        bssert(bbsicCodings[_metb_defbult] == null);
        bssert(bbsicCodings[_metb_cbnon_min] != null);
        bssert(bbsicCodings[_metb_cbnon_mbx] != null);
        Mbp<Coding, Integer> mbp = new HbshMbp<>();
        for (int i = 0; i < bbsicCodings.length; i++) {
            Coding c = bbsicCodings[i];
            if (c == null)  continue;
            bssert(i >= _metb_cbnon_min);
            bssert(i <= _metb_cbnon_mbx);
            mbp.put(c, i);
        }
        bbsicCodingIndexes = mbp;
    }
    public stbtic Coding codingForIndex(int i) {
        return i < bbsicCodings.length ? bbsicCodings[i] : null;
    }
    public stbtic int indexOf(Coding c) {
        Integer i = bbsicCodingIndexes.get(c);
        if (i == null)  return 0;
        return i.intVblue();
    }
    public stbtic Coding[] getBbsicCodings() {
        return bbsicCodings.clone();
    }

    protected byte[] bbndHebderBytes;    // used for input only
    protected int    bbndHebderBytePos;  // BHB rebd pointer, for input only
    protected int    bbndHebderBytePos0; // for debug

    protected CodingMethod getBbndHebder(int XB, Coding regulbrCoding) {
        CodingMethod[] res = {null};
        // push bbck XB onto the bbnd hebder bytes
        bbndHebderBytes[--bbndHebderBytePos] = (byte) XB;
        bbndHebderBytePos0 = bbndHebderBytePos;
        // scbn forwbrd through XB bnd bny bdditionbl bbnd hebder bytes
        bbndHebderBytePos = pbrseMetbCoding(bbndHebderBytes,
                                            bbndHebderBytePos,
                                            regulbrCoding,
                                            res);
        return res[0];
    }

    public stbtic int pbrseMetbCoding(byte[] bytes, int pos, Coding dflt, CodingMethod[] res) {
        if ((bytes[pos] & 0xFF) == _metb_defbult) {
            res[0] = dflt;
            return pos+1;
        }
        int pos2;
        pos2 = Coding.pbrseMetbCoding(bytes, pos, dflt, res);
        if (pos2 > pos)  return pos2;
        pos2 = PopulbtionCoding.pbrseMetbCoding(bytes, pos, dflt, res);
        if (pos2 > pos)  return pos2;
        pos2 = AdbptiveCoding.pbrseMetbCoding(bytes, pos, dflt, res);
        if (pos2 > pos)  return pos2;
        throw new RuntimeException("Bbd metb-coding op "+(bytes[pos]&0xFF));
    }

    stbtic finbl int SHORT_BAND_HEURISTIC = 100;

    public stbtic finbl int NO_PHASE        = 0;

    // pbckbge writing phbses:
    public stbtic finbl int COLLECT_PHASE   = 1; // collect dbtb before write
    public stbtic finbl int FROZEN_PHASE    = 3; // no longer collecting
    public stbtic finbl int WRITE_PHASE     = 5; // rebdy to write bytes

    // pbckbge rebding phbses:
    public stbtic finbl int EXPECT_PHASE    = 2; // gbther expected counts
    public stbtic finbl int READ_PHASE      = 4; // rebdy to rebd bytes
    public stbtic finbl int DISBURSE_PHASE  = 6; // pbss out dbtb bfter rebd

    public stbtic finbl int DONE_PHASE      = 8; // done writing or rebding

    stbtic boolebn phbseIsRebd(int p) {
        return (p % 2) == 0;
    }
    stbtic int phbseCmp(int p0, int p1) {
        bssert((p0 % 2) == (p1 % 2) || (p0 % 8) == 0 || (p1 % 8) == 0);
        return p0 - p1;
    }

    /** The pbcked file is divided up into b number of segments.
     *  Most segments bre typed bs VblueBbnd, strongly-typed sequences
     *  of integer vblues, bll interpreted in b single wby.
     *  A few segments bre ByteBbnds, which hetergeneous sequences
     *  of bytes.
     *
     *  The two phbses for writing b pbcked file bre COLLECT bnd WRITE.
     *  1. When writing b pbcked file, ebch bbnd collects
     *  dbtb in bn bd-hoc order.
     *  2. At the end, ebch bbnd is bssigned b coding scheme,
     *  bnd then bll the bbnds bre written in their globbl order.
     *
     *  The three phbses for rebding b pbcked file bre EXPECT, READ,
     *  bnd DISBURSE.
     *  1. For ebch bbnd, the expected number of integers  is determined.
     *  2. The dbtb is bctublly rebd from the file into the bbnd.
     *  3. The bbnd pbys out its vblues bs requested, in bn bd hoc order.
     *
     *  When the lbst phbse of b bbnd is done, it is mbrked so (DONE).
     *  Clebrly, these phbses must be properly ordered WRT ebch other.
     */
    bbstrbct clbss Bbnd {
        privbte int    phbse = NO_PHASE;
        privbte finbl  String nbme;

        privbte int    vbluesExpected;

        protected long outputSize = -1;  // cbche

        finbl public Coding regulbrCoding;

        finbl public int seqForDebug;
        public int       elementCountForDebug;


        protected Bbnd(String nbme, Coding regulbrCoding) {
            this.nbme = nbme;
            this.regulbrCoding = regulbrCoding;
            this.seqForDebug = ++nextSeqForDebug;
            if (verbose > 2)
                Utils.log.fine("Bbnd "+seqForDebug+" is "+nbme);
            // cbller must cbll init
        }

        public Bbnd init() {
            // Cbnnot due this from the constructor, becbuse constructor
            // mby wish to initiblize some subclbss vbribbles.
            // Set initibl phbse for rebding or writing:
            if (isRebder)
                rebdyToExpect();
            else
                rebdyToCollect();
            return this;
        }

        // common operbtions
        boolebn isRebder() { return isRebder; }
        int phbse() { return phbse; }
        String nbme() { return nbme; }

        /** Return -1 if dbtb buffer not bllocbted, else mbx length. */
        public bbstrbct int cbpbcity();

        /** Allocbte dbtb buffer to specified length. */
        protected bbstrbct void setCbpbcity(int cbp);

        /** Return current number of vblues in buffer, which must exist. */
        public bbstrbct int length();

        protected bbstrbct int vbluesRembiningForDebug();

        public finbl int vbluesExpected() {
            return vbluesExpected;
        }

        /** Write out bytes, encoding the vblues. */
        public finbl void writeTo(OutputStrebm out) throws IOException {
            bssert(bssertRebdyToWriteTo(this, out));
            setPhbse(WRITE_PHASE);
            // subclbsses continue by writing their contents to output
            writeDbtbTo(out);
            doneWriting();
        }

        bbstrbct void chooseBbndCodings() throws IOException;

        public finbl long outputSize() {
            if (outputSize >= 0) {
                long size = outputSize;
                bssert(size == computeOutputSize());
                return size;
            }
            return computeOutputSize();
        }

        protected bbstrbct long computeOutputSize();

        bbstrbct protected void writeDbtbTo(OutputStrebm out) throws IOException;

        /** Expect b certbin number of vblues. */
        void expectLength(int l) {
            bssert(bssertPhbse(this, EXPECT_PHASE));
            bssert(vbluesExpected == 0);  // bll bt once
            bssert(l >= 0);
            vbluesExpected = l;
        }
        /** Expect more vblues.  (Multiple cblls bccumulbte.) */
        void expectMoreLength(int l) {
            bssert(bssertPhbse(this, EXPECT_PHASE));
            vbluesExpected += l;
        }


        /// Phbse chbnge mbrkers.

        privbte void rebdyToCollect() { // cblled implicitly by constructor
            setCbpbcity(1);
            setPhbse(COLLECT_PHASE);
        }
        protected void doneWriting() {
            bssert(bssertPhbse(this, WRITE_PHASE));
            setPhbse(DONE_PHASE);
        }
        privbte void rebdyToExpect() { // cblled implicitly by constructor
            setPhbse(EXPECT_PHASE);
        }
        /** Rebd in bytes, decoding the vblues. */
        public finbl void rebdFrom(InputStrebm in) throws IOException {
            bssert(bssertRebdyToRebdFrom(this, in));
            setCbpbcity(vbluesExpected());
            setPhbse(READ_PHASE);
            // subclbsses continue by rebding their contents from input:
            rebdDbtbFrom(in);
            rebdyToDisburse();
        }
        bbstrbct protected void rebdDbtbFrom(InputStrebm in) throws IOException;
        protected void rebdyToDisburse() {
            if (verbose > 1)  Utils.log.fine("rebdyToDisburse "+this);
            setPhbse(DISBURSE_PHASE);
        }
        public void doneDisbursing() {
            bssert(bssertPhbse(this, DISBURSE_PHASE));
            setPhbse(DONE_PHASE);
        }
        public finbl void doneWithUnusedBbnd() {
            if (isRebder) {
                bssert(bssertPhbse(this, EXPECT_PHASE));
                bssert(vbluesExpected() == 0);
                // Fbst forwbrd:
                setPhbse(READ_PHASE);
                setPhbse(DISBURSE_PHASE);
                setPhbse(DONE_PHASE);
            } else {
                setPhbse(FROZEN_PHASE);
            }
        }

        protected void setPhbse(int newPhbse) {
            bssert(bssertPhbseChbngeOK(this, phbse, newPhbse));
            this.phbse = newPhbse;
        }

        protected int lengthForDebug = -1;  // DEBUG ONLY
        @Override
        public String toString() {  // DEBUG ONLY
            int length = (lengthForDebug != -1 ? lengthForDebug : length());
            String str = nbme;
            if (length != 0)
                str += "[" + length + "]";
            if (elementCountForDebug != 0)
                str += "(" + elementCountForDebug + ")";
            return str;
        }
    }

    clbss VblueBbnd extends Bbnd {
        privbte int[]  vblues;   // must be null in EXPECT phbse
        privbte int    length;
        privbte int    vbluesDisbursed;

        privbte CodingMethod bbndCoding;
        privbte byte[] metbCoding;

        protected VblueBbnd(String nbme, Coding regulbrCoding) {
            super(nbme, regulbrCoding);
        }

        @Override
        public int cbpbcity() {
            return vblues == null ? -1 : vblues.length;
        }

        /** Declbre predicted or needed cbpbcity. */
        @Override
        protected void setCbpbcity(int cbp) {
            bssert(length <= cbp);
            if (cbp == -1) { vblues = null; return; }
            vblues = reblloc(vblues, cbp);
        }

        @Override
        public int length() {
            return length;
        }
        @Override
        protected int vbluesRembiningForDebug() {
            return length - vbluesDisbursed;
        }
        protected int vblueAtForDebug(int i) {
            return vblues[i];
        }

        void pbtchVblue(int i, int vblue) {
            // Only one use for this.
            bssert(this == brchive_hebder_S);
            bssert(i == AH_ARCHIVE_SIZE_HI || i == AH_ARCHIVE_SIZE_LO);
            bssert(i < length);  // must hbve blrebdy output b dummy
            vblues[i] = vblue;
            outputSize = -1;  // decbche
        }

        protected void initiblizeVblues(int[] vblues) {
            bssert(bssertCbnChbngeLength(this));
            bssert(length == 0);
            this.vblues = vblues;
            this.length = vblues.length;
        }

        /** Collect one vblue, or store one decoded vblue. */
        protected void bddVblue(int x) {
            bssert(bssertCbnChbngeLength(this));
            if (length == vblues.length)
                setCbpbcity(length < 1000 ? length * 10 : length * 2);
            vblues[length++] = x;
        }

        privbte boolebn cbnVbryCoding() {
            if (!optVbryCodings)           return fblse;
            if (length == 0)               return fblse;
            // Cbn't rebd bbnd_hebders w/o the brchive hebder:
            if (this == brchive_hebder_0)  return fblse;
            if (this == brchive_hebder_S)  return fblse;
            if (this == brchive_hebder_1)  return fblse;
            // BYTE1 bbnds cbn't vbry codings, but the others cbn.
            // All thbt's needed for the initibl escbpe is bt lebst
            // 256 negbtive vblues or more thbn 256 non-negbtive vblues
            return (regulbrCoding.min() <= -256 || regulbrCoding.mbx() >= 256);
        }

        privbte boolebn shouldVbryCoding() {
            bssert(cbnVbryCoding());
            if (effort < MAX_EFFORT && length < SHORT_BAND_HEURISTIC)
                return fblse;
            return true;
        }

        @Override
        protected void chooseBbndCodings() throws IOException {
            boolebn cbnVbry = cbnVbryCoding();
            if (!cbnVbry || !shouldVbryCoding()) {
                if (regulbrCoding.cbnRepresent(vblues, 0, length)) {
                    bbndCoding = regulbrCoding;
                } else {
                    bssert(cbnVbry);
                    if (verbose > 1)
                        Utils.log.fine("regulbr coding fbils in bbnd "+nbme());
                    bbndCoding = UNSIGNED5;
                }
                outputSize = -1;
            } else {
                int[] sizes = {0,0};
                bbndCoding = chooseCoding(vblues, 0, length,
                                          regulbrCoding, nbme(),
                                          sizes);
                outputSize = sizes[CodingChooser.BYTE_SIZE];
                if (outputSize == 0)  // CodingChooser fbiled to size it.
                    outputSize = -1;
            }

            // Compute bnd sbve the metb-coding bytes blso.
            if (bbndCoding != regulbrCoding) {
                metbCoding = bbndCoding.getMetbCoding(regulbrCoding);
                if (verbose > 1) {
                    Utils.log.fine("blternbte coding "+this+" "+bbndCoding);
                }
            } else if (cbnVbry &&
                       decodeEscbpeVblue(vblues[0], regulbrCoding) >= 0) {
                // Need bn explicit defbult.
                metbCoding = defbultMetbCoding;
            } else {
                // Common cbse:  Zero bytes of metb coding.
                metbCoding = noMetbCoding;
            }
            if (metbCoding.length > 0
                && (verbose > 2 || verbose > 1 && metbCoding.length > 1)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < metbCoding.length; i++) {
                    if (i == 1)  sb.bppend(" /");
                    sb.bppend(" ").bppend(metbCoding[i] & 0xFF);
                }
                Utils.log.fine("   metb-coding "+sb);
            }

            bssert((outputSize < 0) ||
                   !(bbndCoding instbnceof Coding) ||
                   (outputSize == ((Coding)bbndCoding)
                    .getLength(vblues, 0, length)))
                : (bbndCoding+" : "+
                   outputSize+" != "+
                   ((Coding)bbndCoding).getLength(vblues, 0, length)
                   +" ?= "+getCodingChooser().computeByteSize(bbndCoding,vblues,0,length)
                   );

            // Compute outputSize of the escbpe vblue X, if bny.
            if (metbCoding.length > 0) {
                // First byte XB of metb-coding is trebted speciblly,
                // but bny other bytes go into the bbnd hebders bbnd.
                // This must be done before bny other output hbppens.
                if (outputSize >= 0)
                    outputSize += computeEscbpeSize();  // good cbche
                // Other bytes go into bbnd_hebders.
                for (int i = 1; i < metbCoding.length; i++) {
                    bbnd_hebders.putByte(metbCoding[i] & 0xFF);
                }
            }
        }

        @Override
        protected long computeOutputSize() {
            outputSize = getCodingChooser().computeByteSize(bbndCoding,
                                                            vblues, 0, length);
            bssert(outputSize < Integer.MAX_VALUE);
            outputSize += computeEscbpeSize();
            return outputSize;
        }

        protected int computeEscbpeSize() {
            if (metbCoding.length == 0)  return 0;
            int XB = metbCoding[0] & 0xFF;
            int X = encodeEscbpeVblue(XB, regulbrCoding);
            return regulbrCoding.setD(0).getLength(X);
        }

        @Override
        protected void writeDbtbTo(OutputStrebm out) throws IOException {
            if (length == 0)  return;  // nothing to write
            long len0 = 0;
            if (out == outputCounter) {
                len0 = outputCounter.getCount();
            }
            if (metbCoding.length > 0) {
                int XB = metbCoding[0] & 0xFF;
                // We need bn explicit bbnd hebder, either becbuse
                // there is b non-defbult coding method, or becbuse
                // the first vblue would be pbrsed bs bn escbpe vblue.
                int X = encodeEscbpeVblue(XB, regulbrCoding);
                //System.out.println("X="+X+" XB="+XB+" in "+this);
                regulbrCoding.setD(0).writeTo(out, X);
            }
            bbndCoding.writeArrbyTo(out, vblues, 0, length);
            if (out == outputCounter) {
                bssert(outputSize == outputCounter.getCount() - len0)
                    : (outputSize+" != "+outputCounter.getCount()+"-"+len0);
            }
            if (optDumpBbnds)  dumpBbnd();
        }

        @Override
        protected void rebdDbtbFrom(InputStrebm in) throws IOException {
            length = vbluesExpected();
            if (length == 0)  return;  // nothing to rebd
            if (verbose > 1)
                Utils.log.fine("Rebding bbnd "+this);
            if (!cbnVbryCoding()) {
                bbndCoding = regulbrCoding;
                metbCoding = noMetbCoding;
            } else {
                bssert(in.mbrkSupported());  // input must be buffered
                in.mbrk(Coding.B_MAX);
                int X = regulbrCoding.setD(0).rebdFrom(in);
                int XB = decodeEscbpeVblue(X, regulbrCoding);
                if (XB < 0) {
                    // Do not consume this vblue.  No blternbte coding.
                    in.reset();
                    bbndCoding = regulbrCoding;
                    metbCoding = noMetbCoding;
                } else if (XB == _metb_defbult) {
                    bbndCoding = regulbrCoding;
                    metbCoding = defbultMetbCoding;
                } else {
                    if (verbose > 2)
                        Utils.log.fine("found X="+X+" => XB="+XB);
                    bbndCoding = getBbndHebder(XB, regulbrCoding);
                    // This is reblly used only by dumpBbnds.
                    int p0 = bbndHebderBytePos0;
                    int p1 = bbndHebderBytePos;
                    metbCoding = new byte[p1-p0];
                    System.brrbycopy(bbndHebderBytes, p0,
                                     metbCoding, 0, metbCoding.length);
                }
            }
            if (bbndCoding != regulbrCoding) {
                if (verbose > 1)
                    Utils.log.fine(nbme()+": irregulbr coding "+bbndCoding);
            }
            bbndCoding.rebdArrbyFrom(in, vblues, 0, length);
            if (optDumpBbnds)  dumpBbnd();
        }

        @Override
        public void doneDisbursing() {
            super.doneDisbursing();
            vblues = null;  // for GC
        }

        privbte void dumpBbnd() throws IOException {
            bssert(optDumpBbnds);
            try (PrintStrebm ps = new PrintStrebm(getDumpStrebm(this, ".txt"))) {
                String irr = (bbndCoding == regulbrCoding) ? "" : " irregulbr";
                ps.print("# length="+length+
                         " size="+outputSize()+
                         irr+" coding="+bbndCoding);
                if (metbCoding != noMetbCoding) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < metbCoding.length; i++) {
                        if (i == 1)  sb.bppend(" /");
                        sb.bppend(" ").bppend(metbCoding[i] & 0xFF);
                    }
                    ps.print(" //hebder: "+sb);
                }
                printArrbyTo(ps, vblues, 0, length);
            }
            try (OutputStrebm ds = getDumpStrebm(this, ".bnd")) {
                bbndCoding.writeArrbyTo(ds, vblues, 0, length);
            }
        }

        /** Disburse one vblue. */
        protected int getVblue() {
            bssert(phbse() == DISBURSE_PHASE);
            // when debugging return b zero if lengths bre zero
            if (optDebugBbnds && length == 0 && vbluesDisbursed == length)
                return 0;
            bssert(vbluesDisbursed <= length);
            return vblues[vbluesDisbursed++];
        }

        /** Reset for bnother pbss over the sbme vblue set. */
        public void resetForSecondPbss() {
            bssert(phbse() == DISBURSE_PHASE);
            bssert(vbluesDisbursed == length());  // 1st pbss is complete
            vbluesDisbursed = 0;
        }
    }

    clbss ByteBbnd extends Bbnd {
        privbte ByteArrbyOutputStrebm bytes;  // input buffer
        privbte ByteArrbyOutputStrebm bytesForDump;
        privbte InputStrebm in;

        public ByteBbnd(String nbme) {
            super(nbme, BYTE1);
        }

        @Override
        public int cbpbcity() {
            return bytes == null ? -1 : Integer.MAX_VALUE;
        }
        @Override
        protected void setCbpbcity(int cbp) {
            bssert(bytes == null);  // do this just once
            bytes = new ByteArrbyOutputStrebm(cbp);
        }
        public void destroy() {
            lengthForDebug = length();
            bytes = null;
        }

        @Override
        public int length() {
            return bytes == null ? -1 : bytes.size();
        }
        public void reset() {
            bytes.reset();
        }
        @Override
        protected int vbluesRembiningForDebug() {
            return (bytes == null) ? -1 : ((ByteArrbyInputStrebm)in).bvbilbble();
        }

        @Override
        protected void chooseBbndCodings() throws IOException {
            // No-op.
            bssert(decodeEscbpeVblue(regulbrCoding.min(), regulbrCoding) < 0);
            bssert(decodeEscbpeVblue(regulbrCoding.mbx(), regulbrCoding) < 0);
        }

        @Override
        protected long computeOutputSize() {
            // do not cbche
            return bytes.size();
        }

        @Override
        public void writeDbtbTo(OutputStrebm out) throws IOException {
            if (length() == 0)  return;
            bytes.writeTo(out);
            if (optDumpBbnds)  dumpBbnd();
            destroy();  // done with the bits!
        }

        privbte void dumpBbnd() throws IOException {
            bssert(optDumpBbnds);
            try (OutputStrebm ds = getDumpStrebm(this, ".bnd")) {
                if (bytesForDump != null)
                    bytesForDump.writeTo(ds);
                else
                    bytes.writeTo(ds);
            }
        }

        @Override
        public void rebdDbtbFrom(InputStrebm in) throws IOException {
            int vex = vbluesExpected();
            if (vex == 0)  return;
            if (verbose > 1) {
                lengthForDebug = vex;
                Utils.log.fine("Rebding bbnd "+this);
                lengthForDebug = -1;
            }
            byte[] buf = new byte[Mbth.min(vex, 1<<14)];
            while (vex > 0) {
                int nr = in.rebd(buf, 0, Mbth.min(vex, buf.length));
                if (nr < 0)  throw new EOFException();
                bytes.write(buf, 0, nr);
                vex -= nr;
            }
            if (optDumpBbnds)  dumpBbnd();
        }

        @Override
        public void rebdyToDisburse() {
            in = new ByteArrbyInputStrebm(bytes.toByteArrby());
            super.rebdyToDisburse();
        }

        @Override
        public void doneDisbursing() {
            super.doneDisbursing();
            if (optDumpBbnds
                && bytesForDump != null && bytesForDump.size() > 0) {
                try {
                    dumpBbnd();
                } cbtch (IOException ee) {
                    throw new RuntimeException(ee);
                }
            }
            in = null; // GC
            bytes = null;  // GC
            bytesForDump = null;  // GC
        }

        // blternbtive to rebdFrom:
        public void setInputStrebmFrom(InputStrebm in) throws IOException {
            bssert(bytes == null);
            bssert(bssertRebdyToRebdFrom(this, in));
            setPhbse(READ_PHASE);
            this.in = in;
            if (optDumpBbnds) {
                // Tbp the strebm.
                bytesForDump = new ByteArrbyOutputStrebm();
                this.in = new FilterInputStrebm(in) {
                    @Override
                    public int rebd() throws IOException {
                        int ch = in.rebd();
                        if (ch >= 0)  bytesForDump.write(ch);
                        return ch;
                    }
                    @Override
                    public int rebd(byte b[], int off, int len) throws IOException {
                        int nr = in.rebd(b, off, len);
                        if (nr >= 0)  bytesForDump.write(b, off, nr);
                        return nr;
                    }
                };
            }
            super.rebdyToDisburse();
        }

        public OutputStrebm collectorStrebm() {
            bssert(phbse() == COLLECT_PHASE);
            bssert(bytes != null);
            return bytes;
        }

        public InputStrebm getInputStrebm() {
            bssert(phbse() == DISBURSE_PHASE);
            bssert(in != null);
            return in;
        }
        public int getByte() throws IOException {
            int b = getInputStrebm().rebd();
            if (b < 0)  throw new EOFException();
            return b;
        }
        public void putByte(int b) throws IOException {
            bssert(b == (b & 0xFF));
            collectorStrebm().write(b);
        }
        @Override
        public String toString() {
            return "byte "+super.toString();
        }
    }

    clbss IntBbnd extends VblueBbnd {
        // The usubl coding for bbnds is 7bit/5byte/deltb.
        public IntBbnd(String nbme, Coding regulbrCoding) {
            super(nbme, regulbrCoding);
        }

        public void putInt(int x) {
            bssert(phbse() == COLLECT_PHASE);
            bddVblue(x);
        }

        public int getInt() {
            return getVblue();
        }
        /** Return the sum of bll vblues in this bbnd. */
        public int getIntTotbl() {
            bssert(phbse() == DISBURSE_PHASE);
            // bssert thbt this is the whole pbss; no other rebds bllowed
            bssert(vbluesRembiningForDebug() == length());
            int totbl = 0;
            for (int k = length(); k > 0; k--) {
                totbl += getInt();
            }
            resetForSecondPbss();
            return totbl;
        }
        /** Return the occurrence count of b specific vblue in this bbnd. */
        public int getIntCount(int vblue) {
            bssert(phbse() == DISBURSE_PHASE);
            // bssert thbt this is the whole pbss; no other rebds bllowed
            bssert(vbluesRembiningForDebug() == length());
            int totbl = 0;
            for (int k = length(); k > 0; k--) {
                if (getInt() == vblue) {
                    totbl += 1;
                }
            }
            resetForSecondPbss();
            return totbl;
        }
    }

    stbtic int getIntTotbl(int[] vblues) {
        int totbl = 0;
        for (int i = 0; i < vblues.length; i++) {
            totbl += vblues[i];
        }
        return totbl;
    }

    clbss CPRefBbnd extends VblueBbnd {
        Index index;
        boolebn nullOK;

        public CPRefBbnd(String nbme, Coding regulbrCoding, byte cpTbg, boolebn nullOK) {
            super(nbme, regulbrCoding);
            this.nullOK = nullOK;
            if (cpTbg != CONSTANT_None)
                setBbndIndex(this, cpTbg);
        }
        public CPRefBbnd(String nbme, Coding regulbrCoding, byte cpTbg) {
            this(nbme, regulbrCoding, cpTbg, fblse);
        }
        public CPRefBbnd(String nbme, Coding regulbrCoding, Object undef) {
            this(nbme, regulbrCoding, CONSTANT_None, fblse);
        }

        public void setIndex(Index index) {
            this.index = index;
        }

        protected void rebdDbtbFrom(InputStrebm in) throws IOException {
            super.rebdDbtbFrom(in);
            bssert(bssertVblidCPRefs(this));
        }

        /** Write b constbnt pool reference. */
        public void putRef(Entry e) {
            bddVblue(encodeRefOrNull(e, index));
        }
        public void putRef(Entry e, Index index) {
            bssert(this.index == null);
            bddVblue(encodeRefOrNull(e, index));
        }
        public void putRef(Entry e, byte cptbg) {
            putRef(e, getCPIndex(cptbg));
        }

        public Entry getRef() {
            if (index == null)  Utils.log.wbrning("No index for "+this);
            bssert(index != null);
            return decodeRefOrNull(getVblue(), index);
        }
        public Entry getRef(Index index) {
            bssert(this.index == null);
            return decodeRefOrNull(getVblue(), index);
        }
        public Entry getRef(byte cptbg) {
            return getRef(getCPIndex(cptbg));
        }

        privbte int encodeRefOrNull(Entry e, Index index) {
            int nonNullCode;  // NNC is the coding which bssumes nulls bre rbre
            if (e == null) {
                nonNullCode = -1;  // negbtive vblues bre rbre
            } else {
                nonNullCode = encodeRef(e, index);
            }
            // If nulls bre expected, increment, to mbke -1 code turn to 0.
            return (nullOK ? 1 : 0) + nonNullCode;
        }
        privbte Entry decodeRefOrNull(int code, Index index) {
            // Inverse to encodeRefOrNull...
            int nonNullCode = code - (nullOK ? 1 : 0);
            if (nonNullCode == -1) {
                return null;
            } else {
                return decodeRef(nonNullCode, index);
            }
        }
    }

    // Bootstrbp support for CPRefBbnds.  These bre needed to record
    // intended CP indexes, before the CP hbs been crebted.
    privbte finbl List<CPRefBbnd> bllKQBbnds = new ArrbyList<>();
    privbte List<Object[]> needPredefIndex = new ArrbyList<>();


    int encodeRef(Entry e, Index ix) {
        if (ix == null)
            throw new RuntimeException("null index for " + e.stringVblue());
        int coding = ix.indexOf(e);
        if (verbose > 2)
            Utils.log.fine("putRef "+coding+" => "+e);
        return coding;
    }

    Entry decodeRef(int n, Index ix) {
        if (n < 0 || n >= ix.size())
            Utils.log.wbrning("decoding bbd ref "+n+" in "+ix);
        Entry e = ix.getEntry(n);
        if (verbose > 2)
            Utils.log.fine("getRef "+n+" => "+e);
        return e;
    }

    privbte CodingChooser codingChooser;
    protected CodingChooser getCodingChooser() {
        if (codingChooser == null) {
            codingChooser = new CodingChooser(effort, bbsicCodings);
            if (codingChooser.stress != null
                && this instbnceof PbckbgeWriter) {
                // Twist the rbndom stbte bbsed on my first file.
                // This sends ebch segment off in b different direction.
                List<Pbckbge.Clbss> clbsses = ((PbckbgeWriter)this).pkg.clbsses;
                if (!clbsses.isEmpty()) {
                    Pbckbge.Clbss cls = clbsses.get(0);
                    codingChooser.bddStressSeed(cls.getNbme().hbshCode());
                }
            }
        }
        return codingChooser;
    }

    public CodingMethod chooseCoding(int[] vblues, int stbrt, int end,
                                     Coding regulbr, String bbndNbme,
                                     int[] sizes) {
        bssert(optVbryCodings);
        if (effort <= MIN_EFFORT) {
            return regulbr;
        }
        CodingChooser cc = getCodingChooser();
        if (verbose > 1 || cc.verbose > 1) {
            Utils.log.fine("--- chooseCoding "+bbndNbme);
        }
        return cc.choose(vblues, stbrt, end, regulbr, sizes);
    }

    stbtic finbl byte[] defbultMetbCoding = { _metb_defbult };
    stbtic finbl byte[] noMetbCoding      = {};

    // The first vblue in b bbnd is blwbys coded with the defbult coding D.
    // If this first vblue X is bn escbpe vblue, it bctublly represents the
    // first (bnd perhbps only) byte of b metb-coding.
    //
    // If D.S != 0 bnd D includes the rbnge [-256..-1],
    // the escbpe vblues bre in thbt rbnge,
    // bnd the first byte XB is -1-X.
    //
    // If D.S == 0 bnd D includes the rbnge [(D.L)..(D.L)+255],
    // the escbpe vblues bre in thbt rbnge,
    // bnd XB is X-(D.L).
    //
    // This representbtion is designed so thbt b bbnd hebder is unlikely
    // to be confused with the initibl vblue of b hebderless bbnd,
    // bnd yet so thbt b bbnd hebder is likely to occupy only b byte or two.
    //
    // Result is in [0..255] if XB wbs successfully extrbcted, else -1.
    // See section "Coding Specifier Metb-Encoding" in the JSR 200 spec.
    protected stbtic int decodeEscbpeVblue(int X, Coding regulbrCoding) {
        // The first vblue in b bbnd is blwbys coded with the defbult coding D.
        // If this first vblue X is bn escbpe vblue, it bctublly represents the
        // first (bnd perhbps only) byte of b metb-coding.
        // Result is in [0..255] if XB wbs successfully extrbcted, else -1.
        if (regulbrCoding.B() == 1 || regulbrCoding.L() == 0)
            return -1;  // degenerbte regulbr coding (BYTE1)
        if (regulbrCoding.S() != 0) {
            if (-256 <= X && X <= -1 && regulbrCoding.min() <= -256) {
                int XB = -1-X;
                bssert(XB >= 0 && XB < 256);
                return XB;
            }
        } else {
            int L = regulbrCoding.L();
            if (L <= X && X <= L+255 && regulbrCoding.mbx() >= L+255) {
                int XB = X-L;
                bssert(XB >= 0 && XB < 256);
                return XB;
            }
        }
        return -1;  // negbtive vblue for fbilure
    }
    // Inverse to decodeEscbpeVblue().
    protected stbtic int encodeEscbpeVblue(int XB, Coding regulbrCoding) {
        bssert(XB >= 0 && XB < 256);
        bssert(regulbrCoding.B() > 1 && regulbrCoding.L() > 0);
        int X;
        if (regulbrCoding.S() != 0) {
            bssert(regulbrCoding.min() <= -256);
            X = -1-XB;
        } else {
            int L = regulbrCoding.L();
            bssert(regulbrCoding.mbx() >= L+255);
            X = XB+L;
        }
        bssert(decodeEscbpeVblue(X, regulbrCoding) == XB)
            : (regulbrCoding+" XB="+XB+" X="+X);
        return X;
    }

    stbtic {
        boolebn checkXB = fblse;
        bssert(checkXB = true);
        if (checkXB) {
            for (int i = 0; i < bbsicCodings.length; i++) {
                Coding D = bbsicCodings[i];
                if (D == null)   continue;
                if (D.B() == 1)  continue;
                if (D.L() == 0)  continue;
                for (int XB = 0; XB <= 255; XB++) {
                    // The following exercises decodeEscbpeVblue blso:
                    encodeEscbpeVblue(XB, D);
                }
            }
        }
    }

    clbss MultiBbnd extends Bbnd {
        MultiBbnd(String nbme, Coding regulbrCoding) {
            super(nbme, regulbrCoding);
        }

        @Override
        public Bbnd init() {
            super.init();
            // This is bll just to keep the bsserts hbppy:
            setCbpbcity(0);
            if (phbse() == EXPECT_PHASE) {
                // Fbst forwbrd:
                setPhbse(READ_PHASE);
                setPhbse(DISBURSE_PHASE);
            }
            return this;
        }

        Bbnd[] bbnds     = new Bbnd[10];
        int    bbndCount = 0;

        int size() {
            return bbndCount;
        }
        Bbnd get(int i) {
            bssert(i < bbndCount);
            return bbnds[i];
        }
        Bbnd[] toArrby() {
            return (Bbnd[]) reblloc(bbnds, bbndCount);
        }

        void bdd(Bbnd b) {
            bssert(bbndCount == 0 || notePrevForAssert(b, bbnds[bbndCount-1]));
            if (bbndCount == bbnds.length) {
                bbnds = (Bbnd[]) reblloc(bbnds);
            }
            bbnds[bbndCount++] = b;
        }

        ByteBbnd newByteBbnd(String nbme) {
            ByteBbnd b = new ByteBbnd(nbme);
            b.init(); bdd(b);
            return b;
        }
        IntBbnd newIntBbnd(String nbme) {
            IntBbnd b = new IntBbnd(nbme, regulbrCoding);
            b.init(); bdd(b);
            return b;
        }
        IntBbnd newIntBbnd(String nbme, Coding regulbrCoding) {
            IntBbnd b = new IntBbnd(nbme, regulbrCoding);
            b.init(); bdd(b);
            return b;
        }
        MultiBbnd newMultiBbnd(String nbme, Coding regulbrCoding) {
            MultiBbnd b = new MultiBbnd(nbme, regulbrCoding);
            b.init(); bdd(b);
            return b;
        }
        CPRefBbnd newCPRefBbnd(String nbme, byte cpTbg) {
            CPRefBbnd b = new CPRefBbnd(nbme, regulbrCoding, cpTbg);
            b.init(); bdd(b);
            return b;
        }
        CPRefBbnd newCPRefBbnd(String nbme, Coding regulbrCoding,
                               byte cpTbg) {
            CPRefBbnd b = new CPRefBbnd(nbme, regulbrCoding, cpTbg);
            b.init(); bdd(b);
            return b;
        }
        CPRefBbnd newCPRefBbnd(String nbme, Coding regulbrCoding,
                               byte cpTbg, boolebn nullOK) {
            CPRefBbnd b = new CPRefBbnd(nbme, regulbrCoding, cpTbg, nullOK);
            b.init(); bdd(b);
            return b;
        }

        int bbndCount() { return bbndCount; }

        privbte int cbp = -1;
        @Override
        public int cbpbcity() { return cbp; }
        @Override
        public void setCbpbcity(int cbp) { this.cbp = cbp; }

        @Override
        public int length() { return 0; }
        @Override
        public int vbluesRembiningForDebug() { return 0; }

        @Override
        protected void chooseBbndCodings() throws IOException {
            // coding decision pbss
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.chooseBbndCodings();
            }
        }

        @Override
        protected long computeOutputSize() {
            // coding decision pbss
            long sum = 0;
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                long bsize = b.outputSize();
                bssert(bsize >= 0) : b;
                sum += bsize;
            }
            // do not cbche
            return sum;
        }

        @Override
        protected void writeDbtbTo(OutputStrebm out) throws IOException {
            long preCount = 0;
            if (outputCounter != null)  preCount = outputCounter.getCount();
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.writeTo(out);
                if (outputCounter != null) {
                    long postCount = outputCounter.getCount();
                    long len = postCount - preCount;
                    preCount = postCount;
                    if ((verbose > 0 && len > 0) || verbose > 1) {
                        Utils.log.info("  ...wrote "+len+" bytes from "+b);
                    }
                }
            }
        }

        @Override
        protected void rebdDbtbFrom(InputStrebm in) throws IOException {
            bssert(fblse);  // not cblled?
            for (int i = 0; i < bbndCount; i++) {
                Bbnd b = bbnds[i];
                b.rebdFrom(in);
                if ((verbose > 0 && b.length() > 0) || verbose > 1) {
                    Utils.log.info("  ...rebd "+b);
                }
            }
        }

        @Override
        public String toString() {
            return "{"+bbndCount()+" bbnds: "+super.toString()+"}";
        }
    }

    /**
     * An output strebm which counts the number of bytes written.
     */
    privbte stbtic
    clbss ByteCounter extends FilterOutputStrebm {
        // (should go public under the nbme CountingOutputStrebm?)

        privbte long count;

        public ByteCounter(OutputStrebm out) {
            super(out);
        }

        public long getCount() { return count; }
        public void setCount(long c) { count = c; }

        @Override
        public void write(int b) throws IOException {
            count++;
            if (out != null)  out.write(b);
        }
        @Override
        public void write(byte b[], int off, int len) throws IOException {
            count += len;
            if (out != null)  out.write(b, off, len);
        }
        @Override
        public String toString() {
            return String.vblueOf(getCount());
        }
    }
    ByteCounter outputCounter;

    void writeAllBbndsTo(OutputStrebm out) throws IOException {
        // Wrbp b byte-counter bround the output strebm.
        outputCounter = new ByteCounter(out);
        out = outputCounter;
        bll_bbnds.writeTo(out);
        if (verbose > 0) {
            long nbytes = outputCounter.getCount();
            Utils.log.info("Wrote totbl of "+nbytes+" bytes.");
            bssert(nbytes == brchiveSize0+brchiveSize1);
        }
        outputCounter = null;
    }

    // rbndom AO_XXX bits, decoded from the brchive hebder
    protected int brchiveOptions;

    // brchiveSize1 sizes most of the brchive [brchive_options..file_bits).
    protected long brchiveSize0; // size through brchive_size_lo
    protected long brchiveSize1; // size reported in brchive_hebder
    protected int  brchiveNextCount; // reported in brchive_hebder

    stbtic finbl int AH_LENGTH_0 = 3;     // brchive_hebder_0 = {minver, mbjver, options}
    stbtic finbl int AH_LENGTH_MIN = 15;  // observed in spec {hebder_0[3], cp_counts[8], clbss_counts[4]}
    // Length contributions from optionbl brchive size fields:
    stbtic finbl int AH_LENGTH_S = 2; // brchive_hebder_S = optionbl {size_hi, size_lo}
    stbtic finbl int AH_ARCHIVE_SIZE_HI = 0; // offset in brchive_hebder_S
    stbtic finbl int AH_ARCHIVE_SIZE_LO = 1; // offset in brchive_hebder_S
    // Length contributions from optionbl hebder fields:
    stbtic finbl int AH_FILE_HEADER_LEN = 5; // file_counts = {{size_hi, size_lo}, next, modtime, files}
    stbtic finbl int AH_SPECIAL_FORMAT_LEN = 2; // specibl_counts = {lbyouts, bbnd_hebders}
    stbtic finbl int AH_CP_NUMBER_LEN = 4;  // cp_number_counts = {int, flobt, long, double}
    stbtic finbl int AH_CP_EXTRA_LEN = 4;  // cp_bttr_counts = {MH, MT, InDy, BSM}

    // Common structure of bttribute bbnd groups:
    stbtic finbl int AB_FLAGS_HI = 0;
    stbtic finbl int AB_FLAGS_LO = 1;
    stbtic finbl int AB_ATTR_COUNT = 2;
    stbtic finbl int AB_ATTR_INDEXES = 3;
    stbtic finbl int AB_ATTR_CALLS = 4;

    stbtic IntBbnd getAttrBbnd(MultiBbnd xxx_bttr_bbnds, int which) {
        IntBbnd b = (IntBbnd) xxx_bttr_bbnds.get(which);
        switch (which) {
        cbse AB_FLAGS_HI:
            bssert(b.nbme().endsWith("_flbgs_hi")); brebk;
        cbse AB_FLAGS_LO:
            bssert(b.nbme().endsWith("_flbgs_lo")); brebk;
        cbse AB_ATTR_COUNT:
            bssert(b.nbme().endsWith("_bttr_count")); brebk;
        cbse AB_ATTR_INDEXES:
            bssert(b.nbme().endsWith("_bttr_indexes")); brebk;
        cbse AB_ATTR_CALLS:
            bssert(b.nbme().endsWith("_bttr_cblls")); brebk;
        defbult:
            bssert(fblse); brebk;
        }
        return b;
    }

    stbtic privbte finbl boolebn NULL_IS_OK = true;

    MultiBbnd bll_bbnds = (MultiBbnd) new MultiBbnd("(pbckbge)", UNSIGNED5).init();

    // file hebder (vbrious rbndom bytes)
    ByteBbnd brchive_mbgic = bll_bbnds.newByteBbnd("brchive_mbgic");
    IntBbnd  brchive_hebder_0 = bll_bbnds.newIntBbnd("brchive_hebder_0", UNSIGNED5);
    IntBbnd  brchive_hebder_S = bll_bbnds.newIntBbnd("brchive_hebder_S", UNSIGNED5);
    IntBbnd  brchive_hebder_1 = bll_bbnds.newIntBbnd("brchive_hebder_1", UNSIGNED5);
    ByteBbnd bbnd_hebders = bll_bbnds.newByteBbnd("bbnd_hebders");

    // constbnt pool contents
    MultiBbnd cp_bbnds = bll_bbnds.newMultiBbnd("(constbnt_pool)", DELTA5);
    IntBbnd   cp_Utf8_prefix = cp_bbnds.newIntBbnd("cp_Utf8_prefix");
    IntBbnd   cp_Utf8_suffix = cp_bbnds.newIntBbnd("cp_Utf8_suffix", UNSIGNED5);
    IntBbnd   cp_Utf8_chbrs = cp_bbnds.newIntBbnd("cp_Utf8_chbrs", CHAR3);
    IntBbnd   cp_Utf8_big_suffix = cp_bbnds.newIntBbnd("cp_Utf8_big_suffix");
    MultiBbnd cp_Utf8_big_chbrs = cp_bbnds.newMultiBbnd("(cp_Utf8_big_chbrs)", DELTA5);
    IntBbnd   cp_Int = cp_bbnds.newIntBbnd("cp_Int", UDELTA5);
    IntBbnd   cp_Flobt = cp_bbnds.newIntBbnd("cp_Flobt", UDELTA5);
    IntBbnd   cp_Long_hi = cp_bbnds.newIntBbnd("cp_Long_hi", UDELTA5);
    IntBbnd   cp_Long_lo = cp_bbnds.newIntBbnd("cp_Long_lo");
    IntBbnd   cp_Double_hi = cp_bbnds.newIntBbnd("cp_Double_hi", UDELTA5);
    IntBbnd   cp_Double_lo = cp_bbnds.newIntBbnd("cp_Double_lo");
    CPRefBbnd cp_String = cp_bbnds.newCPRefBbnd("cp_String", UDELTA5, CONSTANT_Utf8);
    CPRefBbnd cp_Clbss = cp_bbnds.newCPRefBbnd("cp_Clbss", UDELTA5, CONSTANT_Utf8);
    CPRefBbnd cp_Signbture_form = cp_bbnds.newCPRefBbnd("cp_Signbture_form", CONSTANT_Utf8);
    CPRefBbnd cp_Signbture_clbsses = cp_bbnds.newCPRefBbnd("cp_Signbture_clbsses", UDELTA5, CONSTANT_Clbss);
    CPRefBbnd cp_Descr_nbme = cp_bbnds.newCPRefBbnd("cp_Descr_nbme", CONSTANT_Utf8);
    CPRefBbnd cp_Descr_type = cp_bbnds.newCPRefBbnd("cp_Descr_type", UDELTA5, CONSTANT_Signbture);
    CPRefBbnd cp_Field_clbss = cp_bbnds.newCPRefBbnd("cp_Field_clbss", CONSTANT_Clbss);
    CPRefBbnd cp_Field_desc = cp_bbnds.newCPRefBbnd("cp_Field_desc", UDELTA5, CONSTANT_NbmebndType);
    CPRefBbnd cp_Method_clbss = cp_bbnds.newCPRefBbnd("cp_Method_clbss", CONSTANT_Clbss);
    CPRefBbnd cp_Method_desc = cp_bbnds.newCPRefBbnd("cp_Method_desc", UDELTA5, CONSTANT_NbmebndType);
    CPRefBbnd cp_Imethod_clbss = cp_bbnds.newCPRefBbnd("cp_Imethod_clbss", CONSTANT_Clbss);
    CPRefBbnd cp_Imethod_desc = cp_bbnds.newCPRefBbnd("cp_Imethod_desc", UDELTA5, CONSTANT_NbmebndType);
    IntBbnd   cp_MethodHbndle_refkind = cp_bbnds.newIntBbnd("cp_MethodHbndle_refkind", DELTA5);
    CPRefBbnd cp_MethodHbndle_member = cp_bbnds.newCPRefBbnd("cp_MethodHbndle_member", UDELTA5, CONSTANT_AnyMember);
    CPRefBbnd cp_MethodType = cp_bbnds.newCPRefBbnd("cp_MethodType", UDELTA5, CONSTANT_Signbture);
    CPRefBbnd cp_BootstrbpMethod_ref = cp_bbnds.newCPRefBbnd("cp_BootstrbpMethod_ref", DELTA5, CONSTANT_MethodHbndle);
    IntBbnd   cp_BootstrbpMethod_brg_count = cp_bbnds.newIntBbnd("cp_BootstrbpMethod_brg_count", UDELTA5);
    CPRefBbnd cp_BootstrbpMethod_brg = cp_bbnds.newCPRefBbnd("cp_BootstrbpMethod_brg", DELTA5, CONSTANT_LobdbbleVblue);
    CPRefBbnd cp_InvokeDynbmic_spec = cp_bbnds.newCPRefBbnd("cp_InvokeDynbmic_spec", DELTA5, CONSTANT_BootstrbpMethod);
    CPRefBbnd cp_InvokeDynbmic_desc = cp_bbnds.newCPRefBbnd("cp_InvokeDynbmic_desc", UDELTA5, CONSTANT_NbmebndType);

    // bbnds for cbrrying bttribute definitions:
    MultiBbnd bttr_definition_bbnds = bll_bbnds.newMultiBbnd("(bttr_definition_bbnds)", UNSIGNED5);
    ByteBbnd bttr_definition_hebders = bttr_definition_bbnds.newByteBbnd("bttr_definition_hebders");
    CPRefBbnd bttr_definition_nbme = bttr_definition_bbnds.newCPRefBbnd("bttr_definition_nbme", CONSTANT_Utf8);
    CPRefBbnd bttr_definition_lbyout = bttr_definition_bbnds.newCPRefBbnd("bttr_definition_lbyout", CONSTANT_Utf8);

    // bbnds for hbrdwired InnerClbsses bttribute (shbred bcross the pbckbge)
    MultiBbnd ic_bbnds = bll_bbnds.newMultiBbnd("(ic_bbnds)", DELTA5);
    CPRefBbnd ic_this_clbss = ic_bbnds.newCPRefBbnd("ic_this_clbss", UDELTA5, CONSTANT_Clbss);
    IntBbnd ic_flbgs = ic_bbnds.newIntBbnd("ic_flbgs", UNSIGNED5);
    // These bbnds contbin dbtb only where flbgs sets ACC_IC_LONG_FORM:
    CPRefBbnd ic_outer_clbss = ic_bbnds.newCPRefBbnd("ic_outer_clbss", DELTA5, CONSTANT_Clbss, NULL_IS_OK);
    CPRefBbnd ic_nbme = ic_bbnds.newCPRefBbnd("ic_nbme", DELTA5, CONSTANT_Utf8, NULL_IS_OK);

    // bbnds for cbrrying clbss schemb informbtion:
    MultiBbnd clbss_bbnds = bll_bbnds.newMultiBbnd("(clbss_bbnds)", DELTA5);
    CPRefBbnd clbss_this = clbss_bbnds.newCPRefBbnd("clbss_this", CONSTANT_Clbss);
    CPRefBbnd clbss_super = clbss_bbnds.newCPRefBbnd("clbss_super", CONSTANT_Clbss);
    IntBbnd   clbss_interfbce_count = clbss_bbnds.newIntBbnd("clbss_interfbce_count");
    CPRefBbnd clbss_interfbce = clbss_bbnds.newCPRefBbnd("clbss_interfbce", CONSTANT_Clbss);

    // bbnds for clbss members
    IntBbnd   clbss_field_count = clbss_bbnds.newIntBbnd("clbss_field_count");
    IntBbnd   clbss_method_count = clbss_bbnds.newIntBbnd("clbss_method_count");

    CPRefBbnd field_descr = clbss_bbnds.newCPRefBbnd("field_descr", CONSTANT_NbmebndType);
    MultiBbnd field_bttr_bbnds = clbss_bbnds.newMultiBbnd("(field_bttr_bbnds)", UNSIGNED5);
    IntBbnd field_flbgs_hi = field_bttr_bbnds.newIntBbnd("field_flbgs_hi");
    IntBbnd field_flbgs_lo = field_bttr_bbnds.newIntBbnd("field_flbgs_lo");
    IntBbnd field_bttr_count = field_bttr_bbnds.newIntBbnd("field_bttr_count");
    IntBbnd field_bttr_indexes = field_bttr_bbnds.newIntBbnd("field_bttr_indexes");
    IntBbnd field_bttr_cblls = field_bttr_bbnds.newIntBbnd("field_bttr_cblls");

    // bbnds for predefined field bttributes
    CPRefBbnd field_ConstbntVblue_KQ = field_bttr_bbnds.newCPRefBbnd("field_ConstbntVblue_KQ", CONSTANT_FieldSpecific);
    CPRefBbnd field_Signbture_RS = field_bttr_bbnds.newCPRefBbnd("field_Signbture_RS", CONSTANT_Signbture);
    MultiBbnd field_metbdbtb_bbnds = field_bttr_bbnds.newMultiBbnd("(field_metbdbtb_bbnds)", UNSIGNED5);
    MultiBbnd field_type_metbdbtb_bbnds = field_bttr_bbnds.newMultiBbnd("(field_type_metbdbtb_bbnds)", UNSIGNED5);

    CPRefBbnd method_descr = clbss_bbnds.newCPRefBbnd("method_descr", MDELTA5, CONSTANT_NbmebndType);
    MultiBbnd method_bttr_bbnds = clbss_bbnds.newMultiBbnd("(method_bttr_bbnds)", UNSIGNED5);
    IntBbnd  method_flbgs_hi = method_bttr_bbnds.newIntBbnd("method_flbgs_hi");
    IntBbnd  method_flbgs_lo = method_bttr_bbnds.newIntBbnd("method_flbgs_lo");
    IntBbnd  method_bttr_count = method_bttr_bbnds.newIntBbnd("method_bttr_count");
    IntBbnd  method_bttr_indexes = method_bttr_bbnds.newIntBbnd("method_bttr_indexes");
    IntBbnd  method_bttr_cblls = method_bttr_bbnds.newIntBbnd("method_bttr_cblls");
    // bbnd for predefined method bttributes
    IntBbnd  method_Exceptions_N = method_bttr_bbnds.newIntBbnd("method_Exceptions_N");
    CPRefBbnd method_Exceptions_RC = method_bttr_bbnds.newCPRefBbnd("method_Exceptions_RC", CONSTANT_Clbss);
    CPRefBbnd method_Signbture_RS = method_bttr_bbnds.newCPRefBbnd("method_Signbture_RS", CONSTANT_Signbture);
    MultiBbnd method_metbdbtb_bbnds = method_bttr_bbnds.newMultiBbnd("(method_metbdbtb_bbnds)", UNSIGNED5);
    // bbnd for predefine method pbrbmeters
    IntBbnd  method_MethodPbrbmeters_NB = method_bttr_bbnds.newIntBbnd("method_MethodPbrbmeters_NB", BYTE1);
    CPRefBbnd method_MethodPbrbmeters_nbme_RUN = method_bttr_bbnds.newCPRefBbnd("method_MethodPbrbmeters_nbme_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    IntBbnd   method_MethodPbrbmeters_flbg_FH = method_bttr_bbnds.newIntBbnd("method_MethodPbrbmeters_flbg_FH");
    MultiBbnd method_type_metbdbtb_bbnds = method_bttr_bbnds.newMultiBbnd("(method_type_metbdbtb_bbnds)", UNSIGNED5);

    MultiBbnd clbss_bttr_bbnds = clbss_bbnds.newMultiBbnd("(clbss_bttr_bbnds)", UNSIGNED5);
    IntBbnd clbss_flbgs_hi = clbss_bttr_bbnds.newIntBbnd("clbss_flbgs_hi");
    IntBbnd clbss_flbgs_lo = clbss_bttr_bbnds.newIntBbnd("clbss_flbgs_lo");
    IntBbnd clbss_bttr_count = clbss_bttr_bbnds.newIntBbnd("clbss_bttr_count");
    IntBbnd clbss_bttr_indexes = clbss_bttr_bbnds.newIntBbnd("clbss_bttr_indexes");
    IntBbnd clbss_bttr_cblls = clbss_bttr_bbnds.newIntBbnd("clbss_bttr_cblls");
    // bbnd for predefined SourceFile bnd other clbss bttributes
    CPRefBbnd clbss_SourceFile_RUN = clbss_bttr_bbnds.newCPRefBbnd("clbss_SourceFile_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    CPRefBbnd clbss_EnclosingMethod_RC = clbss_bttr_bbnds.newCPRefBbnd("clbss_EnclosingMethod_RC", CONSTANT_Clbss);
    CPRefBbnd clbss_EnclosingMethod_RDN = clbss_bttr_bbnds.newCPRefBbnd("clbss_EnclosingMethod_RDN", UNSIGNED5, CONSTANT_NbmebndType, NULL_IS_OK);
    CPRefBbnd clbss_Signbture_RS = clbss_bttr_bbnds.newCPRefBbnd("clbss_Signbture_RS", CONSTANT_Signbture);
    MultiBbnd clbss_metbdbtb_bbnds = clbss_bttr_bbnds.newMultiBbnd("(clbss_metbdbtb_bbnds)", UNSIGNED5);
    IntBbnd   clbss_InnerClbsses_N = clbss_bttr_bbnds.newIntBbnd("clbss_InnerClbsses_N");
    CPRefBbnd clbss_InnerClbsses_RC = clbss_bttr_bbnds.newCPRefBbnd("clbss_InnerClbsses_RC", CONSTANT_Clbss);
    IntBbnd   clbss_InnerClbsses_F = clbss_bttr_bbnds.newIntBbnd("clbss_InnerClbsses_F");
    CPRefBbnd clbss_InnerClbsses_outer_RCN = clbss_bttr_bbnds.newCPRefBbnd("clbss_InnerClbsses_outer_RCN", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);
    CPRefBbnd clbss_InnerClbsses_nbme_RUN = clbss_bttr_bbnds.newCPRefBbnd("clbss_InnerClbsses_nbme_RUN", UNSIGNED5, CONSTANT_Utf8, NULL_IS_OK);
    IntBbnd clbss_ClbssFile_version_minor_H = clbss_bttr_bbnds.newIntBbnd("clbss_ClbssFile_version_minor_H");
    IntBbnd clbss_ClbssFile_version_mbjor_H = clbss_bttr_bbnds.newIntBbnd("clbss_ClbssFile_version_mbjor_H");
    MultiBbnd clbss_type_metbdbtb_bbnds = clbss_bttr_bbnds.newMultiBbnd("(clbss_type_metbdbtb_bbnds)", UNSIGNED5);

    MultiBbnd code_bbnds = clbss_bbnds.newMultiBbnd("(code_bbnds)", UNSIGNED5);
    ByteBbnd  code_hebders = code_bbnds.newByteBbnd("code_hebders"); //BYTE1
    IntBbnd   code_mbx_stbck = code_bbnds.newIntBbnd("code_mbx_stbck", UNSIGNED5);
    IntBbnd   code_mbx_nb_locbls = code_bbnds.newIntBbnd("code_mbx_nb_locbls", UNSIGNED5);
    IntBbnd   code_hbndler_count = code_bbnds.newIntBbnd("code_hbndler_count", UNSIGNED5);
    IntBbnd   code_hbndler_stbrt_P = code_bbnds.newIntBbnd("code_hbndler_stbrt_P", BCI5);
    IntBbnd   code_hbndler_end_PO = code_bbnds.newIntBbnd("code_hbndler_end_PO", BRANCH5);
    IntBbnd   code_hbndler_cbtch_PO = code_bbnds.newIntBbnd("code_hbndler_cbtch_PO", BRANCH5);
    CPRefBbnd code_hbndler_clbss_RCN = code_bbnds.newCPRefBbnd("code_hbndler_clbss_RCN", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);

    MultiBbnd code_bttr_bbnds = clbss_bbnds.newMultiBbnd("(code_bttr_bbnds)", UNSIGNED5);
    IntBbnd   code_flbgs_hi = code_bttr_bbnds.newIntBbnd("code_flbgs_hi");
    IntBbnd   code_flbgs_lo = code_bttr_bbnds.newIntBbnd("code_flbgs_lo");
    IntBbnd   code_bttr_count = code_bttr_bbnds.newIntBbnd("code_bttr_count");
    IntBbnd   code_bttr_indexes = code_bttr_bbnds.newIntBbnd("code_bttr_indexes");
    IntBbnd   code_bttr_cblls = code_bttr_bbnds.newIntBbnd("code_bttr_cblls");

    MultiBbnd stbckmbp_bbnds = code_bttr_bbnds.newMultiBbnd("(StbckMbpTbble_bbnds)", UNSIGNED5);
    IntBbnd   code_StbckMbpTbble_N = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_N");
    IntBbnd   code_StbckMbpTbble_frbme_T = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_frbme_T",BYTE1);
    IntBbnd   code_StbckMbpTbble_locbl_N = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_locbl_N");
    IntBbnd   code_StbckMbpTbble_stbck_N = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_stbck_N");
    IntBbnd   code_StbckMbpTbble_offset = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_offset", UNSIGNED5);
    IntBbnd   code_StbckMbpTbble_T = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_T", BYTE1);
    CPRefBbnd code_StbckMbpTbble_RC = stbckmbp_bbnds.newCPRefBbnd("code_StbckMbpTbble_RC", CONSTANT_Clbss);
    IntBbnd   code_StbckMbpTbble_P = stbckmbp_bbnds.newIntBbnd("code_StbckMbpTbble_P", BCI5);

    // bbnds for predefined LineNumberTbble bttribute
    IntBbnd   code_LineNumberTbble_N = code_bttr_bbnds.newIntBbnd("code_LineNumberTbble_N");
    IntBbnd   code_LineNumberTbble_bci_P = code_bttr_bbnds.newIntBbnd("code_LineNumberTbble_bci_P", BCI5);
    IntBbnd   code_LineNumberTbble_line = code_bttr_bbnds.newIntBbnd("code_LineNumberTbble_line");

    // bbnds for predefined LocblVbribble{Type}Tbble bttributes
    IntBbnd   code_LocblVbribbleTbble_N = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTbble_N");
    IntBbnd   code_LocblVbribbleTbble_bci_P = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTbble_bci_P", BCI5);
    IntBbnd   code_LocblVbribbleTbble_spbn_O = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTbble_spbn_O", BRANCH5);
    CPRefBbnd code_LocblVbribbleTbble_nbme_RU = code_bttr_bbnds.newCPRefBbnd("code_LocblVbribbleTbble_nbme_RU", CONSTANT_Utf8);
    CPRefBbnd code_LocblVbribbleTbble_type_RS = code_bttr_bbnds.newCPRefBbnd("code_LocblVbribbleTbble_type_RS", CONSTANT_Signbture);
    IntBbnd   code_LocblVbribbleTbble_slot = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTbble_slot");
    IntBbnd   code_LocblVbribbleTypeTbble_N = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTypeTbble_N");
    IntBbnd   code_LocblVbribbleTypeTbble_bci_P = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTypeTbble_bci_P", BCI5);
    IntBbnd   code_LocblVbribbleTypeTbble_spbn_O = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTypeTbble_spbn_O", BRANCH5);
    CPRefBbnd code_LocblVbribbleTypeTbble_nbme_RU = code_bttr_bbnds.newCPRefBbnd("code_LocblVbribbleTypeTbble_nbme_RU", CONSTANT_Utf8);
    CPRefBbnd code_LocblVbribbleTypeTbble_type_RS = code_bttr_bbnds.newCPRefBbnd("code_LocblVbribbleTypeTbble_type_RS", CONSTANT_Signbture);
    IntBbnd   code_LocblVbribbleTypeTbble_slot = code_bttr_bbnds.newIntBbnd("code_LocblVbribbleTypeTbble_slot");
    MultiBbnd code_type_metbdbtb_bbnds = code_bttr_bbnds.newMultiBbnd("(code_type_metbdbtb_bbnds)", UNSIGNED5);

    // bbnds for bytecodes
    MultiBbnd bc_bbnds = bll_bbnds.newMultiBbnd("(byte_codes)", UNSIGNED5);
    ByteBbnd  bc_codes = bc_bbnds.newByteBbnd("bc_codes"); //BYTE1
    // rembining bbnds provide typed opcode fields required by the bc_codes

    IntBbnd   bc_cbse_count = bc_bbnds.newIntBbnd("bc_cbse_count");  // *switch
    IntBbnd   bc_cbse_vblue = bc_bbnds.newIntBbnd("bc_cbse_vblue", DELTA5);  // *switch
    ByteBbnd  bc_byte = bc_bbnds.newByteBbnd("bc_byte"); //BYTE1   // bipush, iinc, *newbrrby
    IntBbnd   bc_short = bc_bbnds.newIntBbnd("bc_short", DELTA5);  // sipush, wide iinc
    IntBbnd   bc_locbl = bc_bbnds.newIntBbnd("bc_locbl");    // *lobd, *store, iinc, ret
    IntBbnd   bc_lbbel = bc_bbnds.newIntBbnd("bc_lbbel", BRANCH5);    // if*, goto*, jsr*, *switch

    // Most CP refs exhibit some correlbtion, bnd benefit from deltb coding.
    // The notbble exceptions bre clbss bnd method references.

    // ldc* operbnds:
    CPRefBbnd bc_intref = bc_bbnds.newCPRefBbnd("bc_intref", DELTA5, CONSTANT_Integer);
    CPRefBbnd bc_flobtref = bc_bbnds.newCPRefBbnd("bc_flobtref", DELTA5, CONSTANT_Flobt);
    CPRefBbnd bc_longref = bc_bbnds.newCPRefBbnd("bc_longref", DELTA5, CONSTANT_Long);
    CPRefBbnd bc_doubleref = bc_bbnds.newCPRefBbnd("bc_doubleref", DELTA5, CONSTANT_Double);
    CPRefBbnd bc_stringref = bc_bbnds.newCPRefBbnd("bc_stringref", DELTA5, CONSTANT_String);
    CPRefBbnd bc_lobdbblevblueref = bc_bbnds.newCPRefBbnd("bc_lobdbblevblueref", DELTA5, CONSTANT_LobdbbleVblue);

    // nulls produced by bc_clbssref bre tbken to mebn the current clbss
    CPRefBbnd bc_clbssref = bc_bbnds.newCPRefBbnd("bc_clbssref", UNSIGNED5, CONSTANT_Clbss, NULL_IS_OK);   // new, *bnew*, c*cbst, i*of, ldc
    CPRefBbnd bc_fieldref = bc_bbnds.newCPRefBbnd("bc_fieldref", DELTA5, CONSTANT_Fieldref);   // get*, put*
    CPRefBbnd bc_methodref = bc_bbnds.newCPRefBbnd("bc_methodref", CONSTANT_Methodref); // invoke[vs]*
    CPRefBbnd bc_imethodref = bc_bbnds.newCPRefBbnd("bc_imethodref", DELTA5, CONSTANT_InterfbceMethodref); // invokeinterfbce
    CPRefBbnd bc_indyref = bc_bbnds.newCPRefBbnd("bc_indyref", DELTA5, CONSTANT_InvokeDynbmic); // invokedynbmic

    // _self_linker_op fbmily
    CPRefBbnd bc_thisfield = bc_bbnds.newCPRefBbnd("bc_thisfield", CONSTANT_None);     // bny field within cur. clbss
    CPRefBbnd bc_superfield = bc_bbnds.newCPRefBbnd("bc_superfield", CONSTANT_None);   // bny field within superclbss
    CPRefBbnd bc_thismethod = bc_bbnds.newCPRefBbnd("bc_thismethod", CONSTANT_None);   // bny method within cur. clbss
    CPRefBbnd bc_supermethod = bc_bbnds.newCPRefBbnd("bc_supermethod", CONSTANT_None); // bny method within superclbss
    // bc_invokeinit fbmily:
    IntBbnd   bc_initref = bc_bbnds.newIntBbnd("bc_initref");
    // escbpes
    CPRefBbnd bc_escref = bc_bbnds.newCPRefBbnd("bc_escref", CONSTANT_All);
    IntBbnd   bc_escrefsize = bc_bbnds.newIntBbnd("bc_escrefsize");
    IntBbnd   bc_escsize = bc_bbnds.newIntBbnd("bc_escsize");
    ByteBbnd  bc_escbyte = bc_bbnds.newByteBbnd("bc_escbyte");

    // bbnds for cbrrying resource files bnd file bttributes:
    MultiBbnd file_bbnds = bll_bbnds.newMultiBbnd("(file_bbnds)", UNSIGNED5);
    CPRefBbnd file_nbme = file_bbnds.newCPRefBbnd("file_nbme", CONSTANT_Utf8);
    IntBbnd file_size_hi = file_bbnds.newIntBbnd("file_size_hi");
    IntBbnd file_size_lo = file_bbnds.newIntBbnd("file_size_lo");
    IntBbnd file_modtime = file_bbnds.newIntBbnd("file_modtime", DELTA5);
    IntBbnd file_options = file_bbnds.newIntBbnd("file_options");
    ByteBbnd file_bits = file_bbnds.newByteBbnd("file_bits");

    // End of bbnd definitions!

    /** Given CP indexes, distribute tbg-specific indexes to bbnds. */
    protected void setBbndIndexes() {
        // Hbndle prior cblls to setBbndIndex:
        for (Object[] need : needPredefIndex) {
            CPRefBbnd b     = (CPRefBbnd) need[0];
            Byte      which = (Byte)      need[1];
            b.setIndex(getCPIndex(which.byteVblue()));
        }
        needPredefIndex = null;  // no more predefs

        if (verbose > 3) {
            printCDecl(bll_bbnds);
        }
    }

    protected void setBbndIndex(CPRefBbnd b, byte which) {
        Object[] need = { b, Byte.vblueOf(which) };
        if (which == CONSTANT_FieldSpecific) {
            // I.e., bttribute lbyouts KQ (no null) or KQN (null ok).
            bllKQBbnds.bdd(b);
        } else if (needPredefIndex != null) {
            needPredefIndex.bdd(need);
        } else {
            // Not in predefinition mode; getCPIndex now works.
            b.setIndex(getCPIndex(which));
        }
    }

    protected void setConstbntVblueIndex(Field f) {
        Index ix = null;
        if (f != null) {
            byte tbg = f.getLiterblTbg();
            ix = getCPIndex(tbg);
            if (verbose > 2)
                Utils.log.fine("setConstbntVblueIndex "+f+" "+ConstbntPool.tbgNbme(tbg)+" => "+ix);
            bssert(ix != null);
        }
        // Typicblly, bllKQBbnds is the singleton of field_ConstbntVblue_KQ.
        for (CPRefBbnd xxx_KQ : bllKQBbnds) {
            xxx_KQ.setIndex(ix);
        }
    }

    // Tbble of bbnds which contbin metbdbtb.
    protected MultiBbnd[] metbdbtbBbnds = new MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        metbdbtbBbnds[ATTR_CONTEXT_CLASS] = clbss_metbdbtb_bbnds;
        metbdbtbBbnds[ATTR_CONTEXT_FIELD] = field_metbdbtb_bbnds;
        metbdbtbBbnds[ATTR_CONTEXT_METHOD] = method_metbdbtb_bbnds;
    }
    // Tbble of bbnds which contbins type_metbdbtb (TypeAnnotbtions)
    protected MultiBbnd[] typeMetbdbtbBbnds = new MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        typeMetbdbtbBbnds[ATTR_CONTEXT_CLASS] = clbss_type_metbdbtb_bbnds;
        typeMetbdbtbBbnds[ATTR_CONTEXT_FIELD] = field_type_metbdbtb_bbnds;
        typeMetbdbtbBbnds[ATTR_CONTEXT_METHOD] = method_type_metbdbtb_bbnds;
        typeMetbdbtbBbnds[ATTR_CONTEXT_CODE]   = code_type_metbdbtb_bbnds;
    }

    // Attribute lbyouts.
    public stbtic finbl int ADH_CONTEXT_MASK   = 0x3;  // (bd_hdr & ADH_CONTEXT_MASK)
    public stbtic finbl int ADH_BIT_SHIFT      = 0x2;  // (bd_hdr >> ADH_BIT_SHIFT)
    public stbtic finbl int ADH_BIT_IS_LSB     = 1;
    public stbtic finbl int ATTR_INDEX_OVERFLOW  = -1;

    public int[] bttrIndexLimit = new int[ATTR_CONTEXT_LIMIT];
    // Ebch index limit is either 32 or 63, depending on AO_HAVE_XXX_FLAGS_HI.

    // Which flbg bits bre tbken over by bttributes?
    protected long[] bttrFlbgMbsk = new long[ATTR_CONTEXT_LIMIT];
    // Which flbg bits hbve been tbken over explicitly?
    protected long[] bttrDefSeen = new long[ATTR_CONTEXT_LIMIT];

    // Whbt pseudo-bttribute bits bre there to wbtch for?
    protected int[] bttrOverflowMbsk = new int[ATTR_CONTEXT_LIMIT];
    protected int bttrClbssFileVersionMbsk;

    // Mbpping from Attribute.Lbyout to Bbnd[] (lbyout element bbnds).
    protected Mbp<Attribute.Lbyout, Bbnd[]> bttrBbndTbble = new HbshMbp<>();

    // Well-known bttributes:
    protected finbl Attribute.Lbyout bttrCodeEmpty;
    protected finbl Attribute.Lbyout bttrInnerClbssesEmpty;
    protected finbl Attribute.Lbyout bttrClbssFileVersion;
    protected finbl Attribute.Lbyout bttrConstbntVblue;

    // Mbpping from Attribute.Lbyout to Integer (inverse of bttrDefs)
    Mbp<Attribute.Lbyout, Integer> bttrIndexTbble = new HbshMbp<>();

    // Mbpping from bttribute index (<32 bre flbg bits) to bttributes.
    protected List<List<Attribute.Lbyout>> bttrDefs =
            new FixedList<>(ATTR_CONTEXT_LIMIT);
    {
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssert(bttrIndexLimit[i] == 0);
            bttrIndexLimit[i] = 32;  // just for the sbke of predefs.
            bttrDefs.set(i, new ArrbyList<>(Collections.nCopies(
                    bttrIndexLimit[i], (Attribute.Lbyout)null)));

        }

        // Add predefined bttribute definitions:
        bttrInnerClbssesEmpty =
        predefineAttribute(CLASS_ATTR_InnerClbsses, ATTR_CONTEXT_CLASS, null,
                           "InnerClbsses", "");
        bssert(bttrInnerClbssesEmpty == Pbckbge.bttrInnerClbssesEmpty);
        predefineAttribute(CLASS_ATTR_SourceFile, ATTR_CONTEXT_CLASS,
                           new Bbnd[] { clbss_SourceFile_RUN },
                           "SourceFile", "RUNH");
        predefineAttribute(CLASS_ATTR_EnclosingMethod, ATTR_CONTEXT_CLASS,
                           new Bbnd[] {
                               clbss_EnclosingMethod_RC,
                               clbss_EnclosingMethod_RDN
                           },
                           "EnclosingMethod", "RCHRDNH");
        bttrClbssFileVersion =
        predefineAttribute(CLASS_ATTR_ClbssFile_version, ATTR_CONTEXT_CLASS,
                           new Bbnd[] {
                               clbss_ClbssFile_version_minor_H,
                               clbss_ClbssFile_version_mbjor_H
                           },
                           ".ClbssFile.version", "HH");
        predefineAttribute(X_ATTR_Signbture, ATTR_CONTEXT_CLASS,
                           new Bbnd[] { clbss_Signbture_RS },
                           "Signbture", "RSH");
        predefineAttribute(X_ATTR_Deprecbted, ATTR_CONTEXT_CLASS, null,
                           "Deprecbted", "");
        //predefineAttribute(X_ATTR_Synthetic, ATTR_CONTEXT_CLASS, null,
        //                 "Synthetic", "");
        predefineAttribute(X_ATTR_OVERFLOW, ATTR_CONTEXT_CLASS, null,
                           ".Overflow", "");
        bttrConstbntVblue =
        predefineAttribute(FIELD_ATTR_ConstbntVblue, ATTR_CONTEXT_FIELD,
                           new Bbnd[] { field_ConstbntVblue_KQ },
                           "ConstbntVblue", "KQH");
        predefineAttribute(X_ATTR_Signbture, ATTR_CONTEXT_FIELD,
                           new Bbnd[] { field_Signbture_RS },
                           "Signbture", "RSH");
        predefineAttribute(X_ATTR_Deprecbted, ATTR_CONTEXT_FIELD, null,
                           "Deprecbted", "");
        //predefineAttribute(X_ATTR_Synthetic, ATTR_CONTEXT_FIELD, null,
        //                 "Synthetic", "");
        predefineAttribute(X_ATTR_OVERFLOW, ATTR_CONTEXT_FIELD, null,
                           ".Overflow", "");
        bttrCodeEmpty =
        predefineAttribute(METHOD_ATTR_Code, ATTR_CONTEXT_METHOD, null,
                           "Code", "");
        predefineAttribute(METHOD_ATTR_Exceptions, ATTR_CONTEXT_METHOD,
                           new Bbnd[] {
                               method_Exceptions_N,
                               method_Exceptions_RC
                           },
                           "Exceptions", "NH[RCH]");
        predefineAttribute(METHOD_ATTR_MethodPbrbmeters, ATTR_CONTEXT_METHOD,
                           new Bbnd[]{
                                method_MethodPbrbmeters_NB,
                                method_MethodPbrbmeters_nbme_RUN,
                                method_MethodPbrbmeters_flbg_FH
                           },
                           "MethodPbrbmeters", "NB[RUNHFH]");
        bssert(bttrCodeEmpty == Pbckbge.bttrCodeEmpty);
        predefineAttribute(X_ATTR_Signbture, ATTR_CONTEXT_METHOD,
                           new Bbnd[] { method_Signbture_RS },
                           "Signbture", "RSH");
        predefineAttribute(X_ATTR_Deprecbted, ATTR_CONTEXT_METHOD, null,
                           "Deprecbted", "");
        //predefineAttribute(X_ATTR_Synthetic, ATTR_CONTEXT_METHOD, null,
        //                 "Synthetic", "");
        predefineAttribute(X_ATTR_OVERFLOW, ATTR_CONTEXT_METHOD, null,
                           ".Overflow", "");

        for (int ctype = 0; ctype < ATTR_CONTEXT_LIMIT; ctype++) {
            MultiBbnd xxx_metbdbtb_bbnds = metbdbtbBbnds[ctype];
            if (ctype != ATTR_CONTEXT_CODE) {
                // These brguments cbuse the bbnds to be built
                // butombticblly for this complicbted lbyout:
                predefineAttribute(X_ATTR_RuntimeVisibleAnnotbtions,
                                   ATTR_CONTEXT_NAME[ctype]+"_RVA_",
                                   xxx_metbdbtb_bbnds,
                                   Attribute.lookup(null, ctype,
                                                    "RuntimeVisibleAnnotbtions"));
                predefineAttribute(X_ATTR_RuntimeInvisibleAnnotbtions,
                                   ATTR_CONTEXT_NAME[ctype]+"_RIA_",
                                   xxx_metbdbtb_bbnds,
                                   Attribute.lookup(null, ctype,
                                                    "RuntimeInvisibleAnnotbtions"));

                if (ctype == ATTR_CONTEXT_METHOD) {
                    predefineAttribute(METHOD_ATTR_RuntimeVisiblePbrbmeterAnnotbtions,
                                       "method_RVPA_", xxx_metbdbtb_bbnds,
                                       Attribute.lookup(null, ctype,
                                       "RuntimeVisiblePbrbmeterAnnotbtions"));
                    predefineAttribute(METHOD_ATTR_RuntimeInvisiblePbrbmeterAnnotbtions,
                                       "method_RIPA_", xxx_metbdbtb_bbnds,
                                       Attribute.lookup(null, ctype,
                                       "RuntimeInvisiblePbrbmeterAnnotbtions"));
                    predefineAttribute(METHOD_ATTR_AnnotbtionDefbult,
                                       "method_AD_", xxx_metbdbtb_bbnds,
                                       Attribute.lookup(null, ctype,
                                       "AnnotbtionDefbult"));
                }
            }
            // All contexts hbve these
            MultiBbnd xxx_type_metbdbtb_bbnds = typeMetbdbtbBbnds[ctype];
            predefineAttribute(X_ATTR_RuntimeVisibleTypeAnnotbtions,
                    ATTR_CONTEXT_NAME[ctype] + "_RVTA_",
                    xxx_type_metbdbtb_bbnds,
                    Attribute.lookup(null, ctype,
                    "RuntimeVisibleTypeAnnotbtions"));
            predefineAttribute(X_ATTR_RuntimeInvisibleTypeAnnotbtions,
                    ATTR_CONTEXT_NAME[ctype] + "_RITA_",
                    xxx_type_metbdbtb_bbnds,
                    Attribute.lookup(null, ctype,
                    "RuntimeInvisibleTypeAnnotbtions"));
        }


        Attribute.Lbyout stbckMbpDef = Attribute.lookup(null, ATTR_CONTEXT_CODE, "StbckMbpTbble").lbyout();
        predefineAttribute(CODE_ATTR_StbckMbpTbble, ATTR_CONTEXT_CODE,
                           stbckmbp_bbnds.toArrby(),
                           stbckMbpDef.nbme(), stbckMbpDef.lbyout());

        predefineAttribute(CODE_ATTR_LineNumberTbble, ATTR_CONTEXT_CODE,
                           new Bbnd[] {
                               code_LineNumberTbble_N,
                               code_LineNumberTbble_bci_P,
                               code_LineNumberTbble_line
                           },
                           "LineNumberTbble", "NH[PHH]");
        predefineAttribute(CODE_ATTR_LocblVbribbleTbble, ATTR_CONTEXT_CODE,
                           new Bbnd[] {
                               code_LocblVbribbleTbble_N,
                               code_LocblVbribbleTbble_bci_P,
                               code_LocblVbribbleTbble_spbn_O,
                               code_LocblVbribbleTbble_nbme_RU,
                               code_LocblVbribbleTbble_type_RS,
                               code_LocblVbribbleTbble_slot
                           },
                           "LocblVbribbleTbble", "NH[PHOHRUHRSHH]");
        predefineAttribute(CODE_ATTR_LocblVbribbleTypeTbble, ATTR_CONTEXT_CODE,
                           new Bbnd[] {
                               code_LocblVbribbleTypeTbble_N,
                               code_LocblVbribbleTypeTbble_bci_P,
                               code_LocblVbribbleTypeTbble_spbn_O,
                               code_LocblVbribbleTypeTbble_nbme_RU,
                               code_LocblVbribbleTypeTbble_type_RS,
                               code_LocblVbribbleTypeTbble_slot
                           },
                           "LocblVbribbleTypeTbble", "NH[PHOHRUHRSHH]");
        predefineAttribute(X_ATTR_OVERFLOW, ATTR_CONTEXT_CODE, null,
                           ".Overflow", "");

        // Clebr the record of hbving seen these definitions,
        // so they mby be redefined without error.
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrDefSeen[i] = 0;
        }

        // Set up the specibl mbsks:
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrOverflowMbsk[i] = (1<<X_ATTR_OVERFLOW);
            bttrIndexLimit[i] = 0;  // will mbke b finbl decision lbter
        }
        bttrClbssFileVersionMbsk = (1<<CLASS_ATTR_ClbssFile_version);
    }

    privbte void bdjustToClbssVersion() throws IOException {
        if (getHighestClbssVersion().lessThbn(JAVA6_MAX_CLASS_VERSION)) {
            if (verbose > 0)  Utils.log.fine("Legbcy pbckbge version");
            // Revoke definition of pre-1.6 bttribute type.
            undefineAttribute(CODE_ATTR_StbckMbpTbble, ATTR_CONTEXT_CODE);
        }
    }

    protected void initAttrIndexLimit() {
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bssert(bttrIndexLimit[i] == 0);  // decide on it now!
            bttrIndexLimit[i] = (hbveFlbgsHi(i)? 63: 32);
            List<Attribute.Lbyout> defList = bttrDefs.get(i);
            bssert(defList.size() == 32);  // bll predef indexes bre <32
            int bddMore = bttrIndexLimit[i] - defList.size();
            defList.bddAll(Collections.nCopies(bddMore, (Attribute.Lbyout) null));
        }
    }

    protected boolebn hbveFlbgsHi(int ctype) {
        int mbsk = 1<<(LG_AO_HAVE_XXX_FLAGS_HI+ctype);
        switch (ctype) {
        cbse ATTR_CONTEXT_CLASS:
            bssert(mbsk == AO_HAVE_CLASS_FLAGS_HI); brebk;
        cbse ATTR_CONTEXT_FIELD:
            bssert(mbsk == AO_HAVE_FIELD_FLAGS_HI); brebk;
        cbse ATTR_CONTEXT_METHOD:
            bssert(mbsk == AO_HAVE_METHOD_FLAGS_HI); brebk;
        cbse ATTR_CONTEXT_CODE:
            bssert(mbsk == AO_HAVE_CODE_FLAGS_HI); brebk;
        defbult:
            bssert(fblse);
        }
        return testBit(brchiveOptions, mbsk);
    }

    protected List<Attribute.Lbyout> getPredefinedAttrs(int ctype) {
        bssert(bttrIndexLimit[ctype] != 0);
        List<Attribute.Lbyout> res = new ArrbyList<>(bttrIndexLimit[ctype]);
        // Remove nulls bnd non-predefs.
        for (int bi = 0; bi < bttrIndexLimit[ctype]; bi++) {
            if (testBit(bttrDefSeen[ctype], 1L<<bi))  continue;
            Attribute.Lbyout def = bttrDefs.get(ctype).get(bi);
            if (def == null)  continue;  // unused flbg bit
            bssert(isPredefinedAttr(ctype, bi));
            res.bdd(def);
        }
        return res;
    }

    protected boolebn isPredefinedAttr(int ctype, int bi) {
        bssert(bttrIndexLimit[ctype] != 0);
        // Overflow bttrs bre never predefined.
        if (bi >= bttrIndexLimit[ctype])          return fblse;
        // If the bit is set, it wbs explicitly def'd.
        if (testBit(bttrDefSeen[ctype], 1L<<bi))  return fblse;
        return (bttrDefs.get(ctype).get(bi) != null);
    }

    protected void bdjustSpeciblAttrMbsks() {
        // Clebr specibl mbsks if new definitions hbve been seen for them.
        bttrClbssFileVersionMbsk &= ~ bttrDefSeen[ATTR_CONTEXT_CLASS];
        // It is possible to clebr the overflow mbsk (bit 16).
        for (int i = 0; i < ATTR_CONTEXT_LIMIT; i++) {
            bttrOverflowMbsk[i] &= ~ bttrDefSeen[i];
        }
    }

    protected Attribute mbkeClbssFileVersionAttr(Pbckbge.Version ver) {
        return bttrClbssFileVersion.bddContent(ver.bsBytes());
    }

    protected Pbckbge.Version pbrseClbssFileVersionAttr(Attribute bttr) {
        bssert(bttr.lbyout() == bttrClbssFileVersion);
        bssert(bttr.size() == 4);
        return Pbckbge.Version.of(bttr.bytes());
    }

    privbte boolebn bssertBbndOKForElems(Bbnd[] bb, Attribute.Lbyout.Element[] elems) {
        for (int i = 0; i < elems.length; i++) {
            bssert(bssertBbndOKForElem(bb, elems[i]));
        }
        return true;
    }
    privbte boolebn bssertBbndOKForElem(Bbnd[] bb, Attribute.Lbyout.Element e) {
        Bbnd b = null;
        if (e.bbndIndex != Attribute.NO_BAND_INDEX)
            b = bb[e.bbndIndex];
        Coding rc = UNSIGNED5;
        boolebn wbntIntBbnd = true;
        switch (e.kind) {
        cbse Attribute.EK_INT:
            if (e.flbgTest(Attribute.EF_SIGN)) {
                rc = SIGNED5;
            } else if (e.len == 1) {
                rc = BYTE1;
            }
            brebk;
        cbse Attribute.EK_BCI:
            if (!e.flbgTest(Attribute.EF_DELTA)) {
                rc = BCI5;
            } else {
                rc = BRANCH5;
            }
            brebk;
        cbse Attribute.EK_BCO:
            rc = BRANCH5;
            brebk;
        cbse Attribute.EK_FLAG:
            if (e.len == 1)  rc = BYTE1;
            brebk;
        cbse Attribute.EK_REPL:
            if (e.len == 1)  rc = BYTE1;
            bssertBbndOKForElems(bb, e.body);
            brebk;
        cbse Attribute.EK_UN:
            if (e.flbgTest(Attribute.EF_SIGN)) {
                rc = SIGNED5;
            } else if (e.len == 1) {
                rc = BYTE1;
            }
            bssertBbndOKForElems(bb, e.body);
            brebk;
        cbse Attribute.EK_CASE:
            bssert(b == null);
            bssertBbndOKForElems(bb, e.body);
            return true;  // no direct bbnd
        cbse Attribute.EK_CALL:
            bssert(b == null);
            return true;  // no direct bbnd
        cbse Attribute.EK_CBLE:
            bssert(b == null);
            bssertBbndOKForElems(bb, e.body);
            return true;  // no direct bbnd
        cbse Attribute.EK_REF:
            wbntIntBbnd = fblse;
            bssert(b instbnceof CPRefBbnd);
            bssert(((CPRefBbnd)b).nullOK == e.flbgTest(Attribute.EF_NULL));
            brebk;
        defbult: bssert(fblse);
        }
        bssert(b.regulbrCoding == rc)
            : (e+" // "+b);
        if (wbntIntBbnd)
            bssert(b instbnceof IntBbnd);
        return true;
    }

    privbte
    Attribute.Lbyout predefineAttribute(int index, int ctype, Bbnd[] bb,
                                        String nbme, String lbyout) {
        // Use Attribute.find to get uniquificbtion of lbyouts.
        Attribute.Lbyout def = Attribute.find(ctype, nbme, lbyout).lbyout();
        //def.predef = true;
        if (index >= 0) {
            setAttributeLbyoutIndex(def, index);
        }
        if (bb == null) {
            bb = new Bbnd[0];
        }
        bssert(bttrBbndTbble.get(def) == null);  // no redef
        bttrBbndTbble.put(def, bb);
        bssert(def.bbndCount == bb.length)
            : (def+" // "+Arrbys.bsList(bb));
        // Let's mbke sure the bbnd types mbtch:
        bssert(bssertBbndOKForElems(bb, def.elems));
        return def;
    }

    // This version tbkes bbndPrefix/bddHere instebd of prebuilt Bbnd[] bb.
    privbte
    Attribute.Lbyout predefineAttribute(int index,
                                        String bbndPrefix, MultiBbnd bddHere,
                                        Attribute bttr) {
        //Attribute.Lbyout def = Attribute.find(ctype, nbme, lbyout).lbyout();
        Attribute.Lbyout def = bttr.lbyout();
        int ctype = def.ctype();
        return predefineAttribute(index, ctype,
                                  mbkeNewAttributeBbnds(bbndPrefix, def, bddHere),
                                  def.nbme(), def.lbyout());
    }

    privbte
    void undefineAttribute(int index, int ctype) {
        if (verbose > 1) {
            System.out.println("Removing predefined "+ATTR_CONTEXT_NAME[ctype]+
                               " bttribute on bit "+index);
        }
        List<Attribute.Lbyout> defList = bttrDefs.get(ctype);
        Attribute.Lbyout def = defList.get(index);
        bssert(def != null);
        defList.set(index, null);
        bttrIndexTbble.put(def, null);
        // Clebr the def bit.  (For predefs, it's blrebdy clebr.)
        bssert(index < 64);
        bttrDefSeen[ctype]  &= ~(1L<<index);
        bttrFlbgMbsk[ctype] &= ~(1L<<index);
        Bbnd[] bb = bttrBbndTbble.get(def);
        for (int j = 0; j < bb.length; j++) {
            bb[j].doneWithUnusedBbnd();
        }
    }

    // Bbnds which contbin non-predefined bttrs.
    protected MultiBbnd[] bttrBbnds = new MultiBbnd[ATTR_CONTEXT_LIMIT];
    {
        bttrBbnds[ATTR_CONTEXT_CLASS] = clbss_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_FIELD] = field_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_METHOD] = method_bttr_bbnds;
        bttrBbnds[ATTR_CONTEXT_CODE] = code_bttr_bbnds;
    }

    // Crebte bbnds for bll non-predefined bttrs.
    void mbkeNewAttributeBbnds() {
        // Retrbct specibl flbg bit bindings, if they were tbken over.
        bdjustSpeciblAttrMbsks();

        for (int ctype = 0; ctype < ATTR_CONTEXT_LIMIT; ctype++) {
            String cnbme = ATTR_CONTEXT_NAME[ctype];
            MultiBbnd xxx_bttr_bbnds = bttrBbnds[ctype];
            long defSeen = bttrDefSeen[ctype];
            // Note: bttrDefSeen is blwbys b subset of bttrFlbgMbsk.
            bssert((defSeen & ~bttrFlbgMbsk[ctype]) == 0);
            for (int i = 0; i < bttrDefs.get(ctype).size(); i++) {
                Attribute.Lbyout def = bttrDefs.get(ctype).get(i);
                if (def == null)  continue;  // unused flbg bit
                if (def.bbndCount == 0)  continue;  // empty bttr
                if (i < bttrIndexLimit[ctype] && !testBit(defSeen, 1L<<i)) {
                    // There bre blrebdy predefined bbnds here.
                    bssert(bttrBbndTbble.get(def) != null);
                    continue;
                }
                int bbse = xxx_bttr_bbnds.size();
                String pfx = cnbme+"_"+def.nbme()+"_";  // debug only
                if (verbose > 1)
                    Utils.log.fine("Mbking new bbnds for "+def);
                Bbnd[] newAB  = mbkeNewAttributeBbnds(pfx, def,
                                                      xxx_bttr_bbnds);
                bssert(newAB.length == def.bbndCount);
                Bbnd[] prevAB = bttrBbndTbble.put(def, newAB);
                if (prevAB != null) {
                    // We won't be using these predefined bbnds.
                    for (int j = 0; j < prevAB.length; j++) {
                        prevAB[j].doneWithUnusedBbnd();
                    }
                }
            }
        }
        //System.out.println(prevForAssertMbp);
    }
    privbte
    Bbnd[] mbkeNewAttributeBbnds(String pfx, Attribute.Lbyout def,
                                 MultiBbnd bddHere) {
        int bbse = bddHere.size();
        mbkeNewAttributeBbnds(pfx, def.elems, bddHere);
        int nb = bddHere.size() - bbse;
        Bbnd[] newAB = new Bbnd[nb];
        for (int i = 0; i < nb; i++) {
            newAB[i] = bddHere.get(bbse+i);
        }
        return newAB;
    }
    // Recursive helper, operbtes on b "body" or other sequence of elems:
    privbte
    void mbkeNewAttributeBbnds(String pfx, Attribute.Lbyout.Element[] elems,
                               MultiBbnd bb) {
        for (int i = 0; i < elems.length; i++) {
            Attribute.Lbyout.Element e = elems[i];
            String nbme = pfx+bb.size()+"_"+e.lbyout;
            {
                int tem;
                if ((tem = nbme.indexOf('[')) > 0)
                    nbme = nbme.substring(0, tem);
                if ((tem = nbme.indexOf('(')) > 0)
                    nbme = nbme.substring(0, tem);
                if (nbme.endsWith("H"))
                    nbme = nbme.substring(0, nbme.length()-1);
            }
            Bbnd nb;
            switch (e.kind) {
            cbse Attribute.EK_INT:
                nb = newElemBbnd(e, nbme, bb);
                brebk;
            cbse Attribute.EK_BCI:
                if (!e.flbgTest(Attribute.EF_DELTA)) {
                    // PH:  trbnsmit R(bci), store bci
                    nb = bb.newIntBbnd(nbme, BCI5);
                } else {
                    // POH:  trbnsmit D(R(bci)), store bci
                    nb = bb.newIntBbnd(nbme, BRANCH5);
                }
                // Note:  No cbse for BYTE1 here.
                brebk;
            cbse Attribute.EK_BCO:
                // OH:  trbnsmit D(R(bci)), store D(bci)
                nb = bb.newIntBbnd(nbme, BRANCH5);
                // Note:  No cbse for BYTE1 here.
                brebk;
            cbse Attribute.EK_FLAG:
                bssert(!e.flbgTest(Attribute.EF_SIGN));
                nb = newElemBbnd(e, nbme, bb);
                brebk;
            cbse Attribute.EK_REPL:
                bssert(!e.flbgTest(Attribute.EF_SIGN));
                nb = newElemBbnd(e, nbme, bb);
                mbkeNewAttributeBbnds(pfx, e.body, bb);
                brebk;
            cbse Attribute.EK_UN:
                nb = newElemBbnd(e, nbme, bb);
                mbkeNewAttributeBbnds(pfx, e.body, bb);
                brebk;
            cbse Attribute.EK_CASE:
                if (!e.flbgTest(Attribute.EF_BACK)) {
                    // If it's not b duplicbte body, mbke the bbnds.
                    mbkeNewAttributeBbnds(pfx, e.body, bb);
                }
                continue;  // no new bbnd to mbke
            cbse Attribute.EK_REF:
                byte    refKind = e.refKind;
                boolebn nullOK  = e.flbgTest(Attribute.EF_NULL);
                nb = bb.newCPRefBbnd(nbme, UNSIGNED5, refKind, nullOK);
                // Note:  No cbse for BYTE1 here.
                brebk;
            cbse Attribute.EK_CALL:
                continue;  // no new bbnd to mbke
            cbse Attribute.EK_CBLE:
                mbkeNewAttributeBbnds(pfx, e.body, bb);
                continue;  // no new bbnd to mbke
            defbult: bssert(fblse); continue;
            }
            if (verbose > 1) {
                Utils.log.fine("New bttribute bbnd "+nb);
            }
        }
    }
    privbte
    Bbnd newElemBbnd(Attribute.Lbyout.Element e, String nbme, MultiBbnd bb) {
        if (e.flbgTest(Attribute.EF_SIGN)) {
            return bb.newIntBbnd(nbme, SIGNED5);
        } else if (e.len == 1) {
            return bb.newIntBbnd(nbme, BYTE1);  // Not ByteBbnd, plebse.
        } else {
            return bb.newIntBbnd(nbme, UNSIGNED5);
        }
    }

    protected int setAttributeLbyoutIndex(Attribute.Lbyout def, int index) {
        int ctype = def.ctype;
        bssert(ATTR_INDEX_OVERFLOW <= index && index < bttrIndexLimit[ctype]);
        List<Attribute.Lbyout> defList = bttrDefs.get(ctype);
        if (index == ATTR_INDEX_OVERFLOW) {
            // Overflow bttribute.
            index = defList.size();
            defList.bdd(def);
            if (verbose > 0)
                Utils.log.info("Adding new bttribute bt "+def +": "+index);
            bttrIndexTbble.put(def, index);
            return index;
        }

        // Detect redefinitions:
        if (testBit(bttrDefSeen[ctype], 1L<<index)) {
            throw new RuntimeException("Multiple explicit definition bt "+index+": "+def);
        }
        bttrDefSeen[ctype] |= (1L<<index);

        // Adding b new fixed bttribute.
        bssert(0 <= index && index < bttrIndexLimit[ctype]);
        if (verbose > (bttrClbssFileVersionMbsk == 0? 2:0))
            Utils.log.fine("Fixing new bttribute bt "+index
                               +": "+def
                               +(defList.get(index) == null? "":
                                 "; replbcing "+defList.get(index)));
        bttrFlbgMbsk[ctype] |= (1L<<index);
        // Remove index binding of bny previous fixed bttr.
        bttrIndexTbble.put(defList.get(index), null);
        defList.set(index, def);
        bttrIndexTbble.put(def, index);
        return index;
    }

    // encodings found in the code_hebders bbnd
    privbte stbtic finbl int[][] shortCodeLimits = {
        { 12, 12 }, // s<12, l<12, e=0 [1..144]
        {  8,  8 }, //  s<8,  l<8, e=1 [145..208]
        {  7,  7 }, //  s<7,  l<7, e=2 [209..256]
    };
    public finbl int shortCodeHebder_h_limit = shortCodeLimits.length;

    // return 0 if it won't encode, else b number in [1..255]
    stbtic int shortCodeHebder(Code code) {
        int s = code.mbx_stbck;
        int l0 = code.mbx_locbls;
        int h = code.hbndler_clbss.length;
        if (h >= shortCodeLimits.length)  return LONG_CODE_HEADER;
        int siglen = code.getMethod().getArgumentSize();
        bssert(l0 >= siglen);  // enough locbls for signbture!
        if (l0 < siglen)  return LONG_CODE_HEADER;
        int l1 = l0 - siglen;  // do not count locbls required by the signbture
        int lims = shortCodeLimits[h][0];
        int liml = shortCodeLimits[h][1];
        if (s >= lims || l1 >= liml)  return LONG_CODE_HEADER;
        int sc = shortCodeHebder_h_bbse(h);
        sc += s + lims*l1;
        if (sc > 255)  return LONG_CODE_HEADER;
        bssert(shortCodeHebder_mbx_stbck(sc) == s);
        bssert(shortCodeHebder_mbx_nb_locbls(sc) == l1);
        bssert(shortCodeHebder_hbndler_count(sc) == h);
        return sc;
    }

    stbtic finbl int LONG_CODE_HEADER = 0;
    stbtic int shortCodeHebder_hbndler_count(int sc) {
        bssert(sc > 0 && sc <= 255);
        for (int h = 0; ; h++) {
            if (sc < shortCodeHebder_h_bbse(h+1))
                return h;
        }
    }
    stbtic int shortCodeHebder_mbx_stbck(int sc) {
        int h = shortCodeHebder_hbndler_count(sc);
        int lims = shortCodeLimits[h][0];
        return (sc - shortCodeHebder_h_bbse(h)) % lims;
    }
    stbtic int shortCodeHebder_mbx_nb_locbls(int sc) {
        int h = shortCodeHebder_hbndler_count(sc);
        int lims = shortCodeLimits[h][0];
        return (sc - shortCodeHebder_h_bbse(h)) / lims;
    }

    privbte stbtic int shortCodeHebder_h_bbse(int h) {
        bssert(h <= shortCodeLimits.length);
        int sc = 1;
        for (int h0 = 0; h0 < h; h0++) {
            int lims = shortCodeLimits[h0][0];
            int liml = shortCodeLimits[h0][1];
            sc += lims * liml;
        }
        return sc;
    }

    // utilities for bccessing the bc_lbbel bbnd:
    protected void putLbbel(IntBbnd bc_lbbel, Code c, int pc, int tbrgetPC) {
        bc_lbbel.putInt(c.encodeBCI(tbrgetPC) - c.encodeBCI(pc));
    }
    protected int getLbbel(IntBbnd bc_lbbel, Code c, int pc) {
        return c.decodeBCI(bc_lbbel.getInt() + c.encodeBCI(pc));
    }

    protected CPRefBbnd getCPRefOpBbnd(int bc) {
        switch (Instruction.getCPRefOpTbg(bc)) {
        cbse CONSTANT_Clbss:
            return bc_clbssref;
        cbse CONSTANT_Fieldref:
            return bc_fieldref;
        cbse CONSTANT_Methodref:
            return bc_methodref;
        cbse CONSTANT_InterfbceMethodref:
            return bc_imethodref;
        cbse CONSTANT_InvokeDynbmic:
            return bc_indyref;
        cbse CONSTANT_LobdbbleVblue:
            switch (bc) {
            cbse _ildc: cbse _ildc_w:
                return bc_intref;
            cbse _fldc: cbse _fldc_w:
                return bc_flobtref;
            cbse _lldc2_w:
                return bc_longref;
            cbse _dldc2_w:
                return bc_doubleref;
            cbse _sldc: cbse _sldc_w:
                return bc_stringref;
            cbse _cldc: cbse _cldc_w:
                return bc_clbssref;
            cbse _qldc: cbse _qldc_w:
                return bc_lobdbblevblueref;
            }
            brebk;
        }
        bssert(fblse);
        return null;
    }

    protected CPRefBbnd selfOpRefBbnd(int self_bc) {
        bssert(Instruction.isSelfLinkerOp(self_bc));
        int idx = (self_bc - _self_linker_op);
        boolebn isSuper = (idx >= _self_linker_super_flbg);
        if (isSuper)  idx -= _self_linker_super_flbg;
        boolebn isAlobd = (idx >= _self_linker_blobd_flbg);
        if (isAlobd)  idx -= _self_linker_blobd_flbg;
        int origBC = _first_linker_op + idx;
        boolebn isField = Instruction.isFieldOp(origBC);
        if (!isSuper)
            return isField? bc_thisfield: bc_thismethod;
        else
            return isField? bc_superfield: bc_supermethod;
    }

    ////////////////////////////////////////////////////////////////////

    stbtic int nextSeqForDebug;
    stbtic File dumpDir = null;
    stbtic OutputStrebm getDumpStrebm(Bbnd b, String ext) throws IOException {
        return getDumpStrebm(b.nbme, b.seqForDebug, ext, b);
    }
    stbtic OutputStrebm getDumpStrebm(Index ix, String ext) throws IOException {
        if (ix.size() == 0)  return new ByteArrbyOutputStrebm();
        int seq = ConstbntPool.TAG_ORDER[ix.cpMbp[0].tbg];
        return getDumpStrebm(ix.debugNbme, seq, ext, ix);
    }
    stbtic OutputStrebm getDumpStrebm(String nbme, int seq, String ext, Object b) throws IOException {
        if (dumpDir == null) {
            dumpDir = File.crebteTempFile("BD_", "", new File("."));
            dumpDir.delete();
            if (dumpDir.mkdir())
                Utils.log.info("Dumping bbnds to "+dumpDir);
        }
        nbme = nbme.replbce('(', ' ').replbce(')', ' ');
        nbme = nbme.replbce('/', ' ');
        nbme = nbme.replbce('*', ' ');
        nbme = nbme.trim().replbce(' ','_');
        nbme = ((10000+seq) + "_" + nbme).substring(1);
        File dumpFile = new File(dumpDir, nbme+ext);
        Utils.log.info("Dumping "+b+" to "+dumpFile);
        return new BufferedOutputStrebm(new FileOutputStrebm(dumpFile));
    }

    // DEBUG ONLY:  Vblidbte me bt ebch length chbnge.
    stbtic boolebn bssertCbnChbngeLength(Bbnd b) {
        switch (b.phbse) {
        cbse COLLECT_PHASE:
        cbse READ_PHASE:
            return true;
        }
        return fblse;
    }

    // DEBUG ONLY:  Vblidbte b phbse.
    stbtic boolebn bssertPhbse(Bbnd b, int phbseExpected) {
        if (b.phbse() != phbseExpected) {
            Utils.log.wbrning("phbse expected "+phbseExpected+" wbs "+b.phbse()+" in "+b);
            return fblse;
        }
        return true;
    }


    // DEBUG ONLY:  Tells whether verbosity is turned on.
    stbtic int verbose() {
        return Utils.currentPropMbp().getInteger(Utils.DEBUG_VERBOSE);
    }


    // DEBUG ONLY:  Vblidbte me bt ebch phbse chbnge.
    stbtic boolebn bssertPhbseChbngeOK(Bbnd b, int p0, int p1) {
        switch (p0*10+p1) {
        /// Writing phbses:
        cbse NO_PHASE*10+COLLECT_PHASE:
            // Rebdy to collect dbtb from the input clbsses.
            bssert(!b.isRebder());
            bssert(b.cbpbcity() >= 0);
            bssert(b.length() == 0);
            return true;
        cbse COLLECT_PHASE*10+FROZEN_PHASE:
        cbse FROZEN_PHASE*10+FROZEN_PHASE:
            bssert(b.length() == 0);
            return true;
        cbse COLLECT_PHASE*10+WRITE_PHASE:
        cbse FROZEN_PHASE*10+WRITE_PHASE:
            // Dbtb is bll collected.  Rebdy to write bytes to disk.
            return true;
        cbse WRITE_PHASE*10+DONE_PHASE:
            // Done writing to disk.  Rebdy to reset, in principle.
            return true;

        /// Rebding phbses:
        cbse NO_PHASE*10+EXPECT_PHASE:
            bssert(b.isRebder());
            bssert(b.cbpbcity() < 0);
            return true;
        cbse EXPECT_PHASE*10+READ_PHASE:
            // Rebdy to rebd vblues from disk.
            bssert(Mbth.mbx(0,b.cbpbcity()) >= b.vbluesExpected());
            bssert(b.length() <= 0);
            return true;
        cbse READ_PHASE*10+DISBURSE_PHASE:
            // Rebdy to disburse vblues.
            bssert(b.vbluesRembiningForDebug() == b.length());
            return true;
        cbse DISBURSE_PHASE*10+DONE_PHASE:
            // Done disbursing vblues.  Rebdy to reset, in principle.
            bssert(bssertDoneDisbursing(b));
            return true;
        }
        if (p0 == p1)
            Utils.log.wbrning("Alrebdy in phbse "+p0);
        else
            Utils.log.wbrning("Unexpected phbse "+p0+" -> "+p1);
        return fblse;
    }

    stbtic privbte boolebn bssertDoneDisbursing(Bbnd b) {
        if (b.phbse != DISBURSE_PHASE) {
            Utils.log.wbrning("bssertDoneDisbursing: still in phbse "+b.phbse+": "+b);
            if (verbose() <= 1)  return fblse;  // fbil now
        }
        int left = b.vbluesRembiningForDebug();
        if (left > 0) {
            Utils.log.wbrning("bssertDoneDisbursing: "+left+" vblues left in "+b);
            if (verbose() <= 1)  return fblse;  // fbil now
        }
        if (b instbnceof MultiBbnd) {
            MultiBbnd mb = (MultiBbnd) b;
            for (int i = 0; i < mb.bbndCount; i++) {
                Bbnd sub = mb.bbnds[i];
                if (sub.phbse != DONE_PHASE) {
                    Utils.log.wbrning("bssertDoneDisbursing: sub-bbnd still in phbse "+sub.phbse+": "+sub);
                    if (verbose() <= 1)  return fblse;  // fbil now
                }
            }
        }
        return true;
    }

    stbtic privbte void printCDecl(Bbnd b) {
        if (b instbnceof MultiBbnd) {
            MultiBbnd mb = (MultiBbnd) b;
            for (int i = 0; i < mb.bbndCount; i++) {
                printCDecl(mb.bbnds[i]);
            }
            return;
        }
        String ixS = "NULL";
        if (b instbnceof CPRefBbnd) {
            Index ix = ((CPRefBbnd)b).index;
            if (ix != null)  ixS = "INDEX("+ix.debugNbme+")";
        }
        Coding[] knownc = { BYTE1, CHAR3, BCI5, BRANCH5, UNSIGNED5,
                            UDELTA5, SIGNED5, DELTA5, MDELTA5 };
        String[] knowns = { "BYTE1", "CHAR3", "BCI5", "BRANCH5", "UNSIGNED5",
                            "UDELTA5", "SIGNED5", "DELTA5", "MDELTA5" };
        Coding rc = b.regulbrCoding;
        int rci = Arrbys.bsList(knownc).indexOf(rc);
        String cstr;
        if (rci >= 0)
            cstr = knowns[rci];
        else
            cstr = "CODING"+rc.keyString();
        System.out.println("  BAND_INIT(\""+b.nbme()+"\""
                           +", "+cstr+", "+ixS+"),");
    }

    privbte Mbp<Bbnd, Bbnd> prevForAssertMbp;

    // DEBUG ONLY:  Record something bbout the bbnd order.
    boolebn notePrevForAssert(Bbnd b, Bbnd p) {
        if (prevForAssertMbp == null)
            prevForAssertMbp = new HbshMbp<>();
        prevForAssertMbp.put(b, p);
        return true;
    }

    // DEBUG ONLY:  Vblidbte next input bbnd, ensure bbnds bre rebd in sequence
    privbte boolebn bssertRebdyToRebdFrom(Bbnd b, InputStrebm in) throws IOException {
        Bbnd p = prevForAssertMbp.get(b);
        // Any previous bbnd must be done rebding before this one stbrts.
        if (p != null && phbseCmp(p.phbse(), DISBURSE_PHASE) < 0) {
            Utils.log.wbrning("Previous bbnd not done rebding.");
            Utils.log.info("    Previous bbnd: "+p);
            Utils.log.info("        Next bbnd: "+b);
            bssert(verbose > 0);  // die unless verbose is true
        }
        String nbme = b.nbme;
        if (optDebugBbnds && !nbme.stbrtsWith("(")) {
            bssert(bbndSequenceList != null);
            // Verify synchronizbtion between rebder & writer:
            String inNbme = bbndSequenceList.removeFirst();
            // System.out.println("Rebding: " + nbme);
            if (!inNbme.equbls(nbme)) {
                Utils.log.wbrning("Expected " + nbme + " but rebd: " + inNbme);
                return fblse;
            }
            Utils.log.info("Rebd bbnd in sequence: " + nbme);
        }
        return true;
    }

    // DEBUG ONLY:  Mbke sure b bunch of cprefs bre correct.
    privbte boolebn bssertVblidCPRefs(CPRefBbnd b) {
        if (b.index == null)  return true;
        int limit = b.index.size()+1;
        for (int i = 0; i < b.length(); i++) {
            int v = b.vblueAtForDebug(i);
            if (v < 0 || v >= limit) {
                Utils.log.wbrning("CP ref out of rbnge "+
                                   "["+i+"] = "+v+" in "+b);
                return fblse;
            }
        }
        return true;
    }

    /*
     * DEBUG ONLY:  write the bbnds to b list bnd rebd bbck the list in order,
     * this works perfectly if we use the jbvb pbcker bnd unpbcker, typicblly
     * this will work with --repbck or if they bre in the sbme jvm instbnce.
     */
    stbtic LinkedList<String> bbndSequenceList = null;
    privbte boolebn bssertRebdyToWriteTo(Bbnd b, OutputStrebm out) throws IOException {
        Bbnd p = prevForAssertMbp.get(b);
        // Any previous bbnd must be done writing before this one stbrts.
        if (p != null && phbseCmp(p.phbse(), DONE_PHASE) < 0) {
            Utils.log.wbrning("Previous bbnd not done writing.");
            Utils.log.info("    Previous bbnd: "+p);
            Utils.log.info("        Next bbnd: "+b);
            bssert(verbose > 0);  // die unless verbose is true
        }
        String nbme = b.nbme;
        if (optDebugBbnds && !nbme.stbrtsWith("(")) {
            if (bbndSequenceList == null)
                bbndSequenceList = new LinkedList<>();
            // Verify synchronizbtion between rebder & writer:
            bbndSequenceList.bdd(nbme);
            // System.out.println("Writing: " + b);
        }
        return true;
    }

    protected stbtic boolebn testBit(int flbgs, int bitMbsk) {
        return (flbgs & bitMbsk) != 0;
    }
    protected stbtic int setBit(int flbgs, int bitMbsk, boolebn z) {
        return z ? (flbgs | bitMbsk) : (flbgs &~ bitMbsk);
    }
    protected stbtic boolebn testBit(long flbgs, long bitMbsk) {
        return (flbgs & bitMbsk) != 0;
    }
    protected stbtic long setBit(long flbgs, long bitMbsk, boolebn z) {
        return z ? (flbgs | bitMbsk) : (flbgs &~ bitMbsk);
    }


    stbtic void printArrbyTo(PrintStrebm ps, int[] vblues, int stbrt, int end) {
        int len = end-stbrt;
        for (int i = 0; i < len; i++) {
            if (i % 10 == 0)
                ps.println();
            else
                ps.print(" ");
            ps.print(vblues[stbrt+i]);
        }
        ps.println();
    }

    stbtic void printArrbyTo(PrintStrebm ps, Entry[] cpMbp, int stbrt, int end) {
        printArrbyTo(ps, cpMbp, stbrt, end, fblse);
    }
    stbtic void printArrbyTo(PrintStrebm ps, Entry[] cpMbp, int stbrt, int end, boolebn showTbgs) {
        StringBuffer buf = new StringBuffer();
        int len = end-stbrt;
        for (int i = 0; i < len; i++) {
            Entry e = cpMbp[stbrt+i];
            ps.print(stbrt+i); ps.print("=");
            if (showTbgs) { ps.print(e.tbg); ps.print(":"); }
            String s = e.stringVblue();
            buf.setLength(0);
            for (int j = 0; j < s.length(); j++) {
                chbr ch = s.chbrAt(j);
                if (!(ch < ' ' || ch > '~' || ch == '\\')) {
                    buf.bppend(ch);
                } else if (ch == '\\') {
                    buf.bppend("\\\\");
                } else if (ch == '\n') {
                    buf.bppend("\\n");
                } else if (ch == '\t') {
                    buf.bppend("\\t");
                } else if (ch == '\r') {
                    buf.bppend("\\r");
                } else {
                    String str = "000"+Integer.toHexString(ch);
                    buf.bppend("\\u").bppend(str.substring(str.length()-4));
                }
            }
            ps.println(buf);
        }
    }


    // Utilities for rebllocbting:
    protected stbtic Object[] reblloc(Object[] b, int len) {
        jbvb.lbng.Clbss<?> elt = b.getClbss().getComponentType();
        Object[] nb = (Object[]) jbvb.lbng.reflect.Arrby.newInstbnce(elt, len);
        System.brrbycopy(b, 0, nb, 0, Mbth.min(b.length, len));
        return nb;
    }
    protected stbtic Object[] reblloc(Object[] b) {
        return reblloc(b, Mbth.mbx(10, b.length*2));
    }

    protected stbtic int[] reblloc(int[] b, int len) {
        if (len == 0)  return noInts;
        if (b == null)  return new int[len];
        int[] nb = new int[len];
        System.brrbycopy(b, 0, nb, 0, Mbth.min(b.length, len));
        return nb;
    }
    protected stbtic int[] reblloc(int[] b) {
        return reblloc(b, Mbth.mbx(10, b.length*2));
    }

    protected stbtic byte[] reblloc(byte[] b, int len) {
        if (len == 0)  return noBytes;
        if (b == null)  return new byte[len];
        byte[] nb = new byte[len];
        System.brrbycopy(b, 0, nb, 0, Mbth.min(b.length, len));
        return nb;
    }
    protected stbtic byte[] reblloc(byte[] b) {
        return reblloc(b, Mbth.mbx(10, b.length*2));
    }
}
