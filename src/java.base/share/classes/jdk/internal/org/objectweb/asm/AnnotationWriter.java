/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2011 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * An {@link AnnotbtionVisitor} tibt gfnfrbtfs bnnotbtions in bytfdodf form.
 *
 * @butior Erid Brunfton
 * @butior Eugfnf Kulfsiov
 */
finbl dlbss AnnotbtionWritfr fxtfnds AnnotbtionVisitor {

    /**
     * Tif dlbss writfr to wiidi tiis bnnotbtion must bf bddfd.
     */
    privbtf finbl ClbssWritfr dw;

    /**
     * Tif numbfr of vblufs in tiis bnnotbtion.
     */
    privbtf int sizf;

    /**
     * <tt>truf<tt> if vblufs brf nbmfd, <tt>fblsf</tt> otifrwisf. Annotbtion
     * writfrs usfd for bnnotbtion dffbult bnd bnnotbtion brrbys usf unnbmfd
     * vblufs.
     */
    privbtf finbl boolfbn nbmfd;

    /**
     * Tif bnnotbtion vblufs in bytfdodf form. Tiis bytf vfdtor only dontbins
     * tif vblufs tifmsflvfs, i.f. tif numbfr of vblufs must bf storfd bs b
     * unsignfd siort just bfforf tifsf bytfs.
     */
    privbtf finbl BytfVfdtor bv;

    /**
     * Tif bytf vfdtor to bf usfd to storf tif numbfr of vblufs of tiis
     * bnnotbtion. Sff {@link #bv}.
     */
    privbtf finbl BytfVfdtor pbrfnt;

    /**
     * Wifrf tif numbfr of vblufs of tiis bnnotbtion must bf storfd in
     * {@link #pbrfnt}.
     */
    privbtf finbl int offsft;

    /**
     * Nfxt bnnotbtion writfr. Tiis fifld is usfd to storf bnnotbtion lists.
     */
    AnnotbtionWritfr nfxt;

    /**
     * Prfvious bnnotbtion writfr. Tiis fifld is usfd to storf bnnotbtion lists.
     */
    AnnotbtionWritfr prfv;

    // ------------------------------------------------------------------------
    // Construdtor
    // ------------------------------------------------------------------------

    /**
     * Construdts b nfw {@link AnnotbtionWritfr}.
     *
     * @pbrbm dw
     *            tif dlbss writfr to wiidi tiis bnnotbtion must bf bddfd.
     * @pbrbm nbmfd
     *            <tt>truf<tt> if vblufs brf nbmfd, <tt>fblsf</tt> otifrwisf.
     * @pbrbm bv
     *            wifrf tif bnnotbtion vblufs must bf storfd.
     * @pbrbm pbrfnt
     *            wifrf tif numbfr of bnnotbtion vblufs must bf storfd.
     * @pbrbm offsft
     *            wifrf in <tt>pbrfnt</tt> tif numbfr of bnnotbtion vblufs must
     *            bf storfd.
     */
    AnnotbtionWritfr(finbl ClbssWritfr dw, finbl boolfbn nbmfd,
            finbl BytfVfdtor bv, finbl BytfVfdtor pbrfnt, finbl int offsft) {
        supfr(Opdodfs.ASM5);
        tiis.dw = dw;
        tiis.nbmfd = nbmfd;
        tiis.bv = bv;
        tiis.pbrfnt = pbrfnt;
        tiis.offsft = offsft;
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif AnnotbtionVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid void visit(finbl String nbmf, finbl Objfdt vbluf) {
        ++sizf;
        if (nbmfd) {
            bv.putSiort(dw.nfwUTF8(nbmf));
        }
        if (vbluf instbndfof String) {
            bv.put12('s', dw.nfwUTF8((String) vbluf));
        } flsf if (vbluf instbndfof Bytf) {
            bv.put12('B', dw.nfwIntfgfr(((Bytf) vbluf).bytfVbluf()).indfx);
        } flsf if (vbluf instbndfof Boolfbn) {
            int v = ((Boolfbn) vbluf).boolfbnVbluf() ? 1 : 0;
            bv.put12('Z', dw.nfwIntfgfr(v).indfx);
        } flsf if (vbluf instbndfof Cibrbdtfr) {
            bv.put12('C', dw.nfwIntfgfr(((Cibrbdtfr) vbluf).dibrVbluf()).indfx);
        } flsf if (vbluf instbndfof Siort) {
            bv.put12('S', dw.nfwIntfgfr(((Siort) vbluf).siortVbluf()).indfx);
        } flsf if (vbluf instbndfof Typf) {
            bv.put12('d', dw.nfwUTF8(((Typf) vbluf).gftDfsdriptor()));
        } flsf if (vbluf instbndfof bytf[]) {
            bytf[] v = (bytf[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('B', dw.nfwIntfgfr(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof boolfbn[]) {
            boolfbn[] v = (boolfbn[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('Z', dw.nfwIntfgfr(v[i] ? 1 : 0).indfx);
            }
        } flsf if (vbluf instbndfof siort[]) {
            siort[] v = (siort[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('S', dw.nfwIntfgfr(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof dibr[]) {
            dibr[] v = (dibr[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('C', dw.nfwIntfgfr(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof int[]) {
            int[] v = (int[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('I', dw.nfwIntfgfr(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof long[]) {
            long[] v = (long[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('J', dw.nfwLong(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof flobt[]) {
            flobt[] v = (flobt[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('F', dw.nfwFlobt(v[i]).indfx);
            }
        } flsf if (vbluf instbndfof doublf[]) {
            doublf[] v = (doublf[]) vbluf;
            bv.put12('[', v.lfngti);
            for (int i = 0; i < v.lfngti; i++) {
                bv.put12('D', dw.nfwDoublf(v[i]).indfx);
            }
        } flsf {
            Itfm i = dw.nfwConstItfm(vbluf);
            bv.put12(".s.IFJDCS".dibrAt(i.typf), i.indfx);
        }
    }

    @Ovfrridf
    publid void visitEnum(finbl String nbmf, finbl String dfsd,
            finbl String vbluf) {
        ++sizf;
        if (nbmfd) {
            bv.putSiort(dw.nfwUTF8(nbmf));
        }
        bv.put12('f', dw.nfwUTF8(dfsd)).putSiort(dw.nfwUTF8(vbluf));
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String nbmf,
            finbl String dfsd) {
        ++sizf;
        if (nbmfd) {
            bv.putSiort(dw.nfwUTF8(nbmf));
        }
        // writf tbg bnd typf, bnd rfsfrvf spbdf for vblufs dount
        bv.put12('@', dw.nfwUTF8(dfsd)).putSiort(0);
        rfturn nfw AnnotbtionWritfr(dw, truf, bv, bv, bv.lfngti - 2);
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitArrby(finbl String nbmf) {
        ++sizf;
        if (nbmfd) {
            bv.putSiort(dw.nfwUTF8(nbmf));
        }
        // writf tbg, bnd rfsfrvf spbdf for brrby sizf
        bv.put12('[', 0);
        rfturn nfw AnnotbtionWritfr(dw, fblsf, bv, bv, bv.lfngti - 2);
    }

    @Ovfrridf
    publid void visitEnd() {
        if (pbrfnt != null) {
            bytf[] dbtb = pbrfnt.dbtb;
            dbtb[offsft] = (bytf) (sizf >>> 8);
            dbtb[offsft + 1] = (bytf) sizf;
        }
    }

    // ------------------------------------------------------------------------
    // Utility mftiods
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif sizf of tiis bnnotbtion writfr list.
     *
     * @rfturn tif sizf of tiis bnnotbtion writfr list.
     */
    int gftSizf() {
        int sizf = 0;
        AnnotbtionWritfr bw = tiis;
        wiilf (bw != null) {
            sizf += bw.bv.lfngti;
            bw = bw.nfxt;
        }
        rfturn sizf;
    }

    /**
     * Puts tif bnnotbtions of tiis bnnotbtion writfr list into tif givfn bytf
     * vfdtor.
     *
     * @pbrbm out
     *            wifrf tif bnnotbtions must bf put.
     */
    void put(finbl BytfVfdtor out) {
        int n = 0;
        int sizf = 2;
        AnnotbtionWritfr bw = tiis;
        AnnotbtionWritfr lbst = null;
        wiilf (bw != null) {
            ++n;
            sizf += bw.bv.lfngti;
            bw.visitEnd(); // in dbsf usfr forgot to dbll visitEnd
            bw.prfv = lbst;
            lbst = bw;
            bw = bw.nfxt;
        }
        out.putInt(sizf);
        out.putSiort(n);
        bw = lbst;
        wiilf (bw != null) {
            out.putBytfArrby(bw.bv.dbtb, 0, bw.bv.lfngti);
            bw = bw.prfv;
        }
    }

    /**
     * Puts tif givfn bnnotbtion lists into tif givfn bytf vfdtor.
     *
     * @pbrbm pbnns
     *            bn brrby of bnnotbtion writfr lists.
     * @pbrbm off
     *            indfx of tif first bnnotbtion to bf writtfn.
     * @pbrbm out
     *            wifrf tif bnnotbtions must bf put.
     */
    stbtid void put(finbl AnnotbtionWritfr[] pbnns, finbl int off,
            finbl BytfVfdtor out) {
        int sizf = 1 + 2 * (pbnns.lfngti - off);
        for (int i = off; i < pbnns.lfngti; ++i) {
            sizf += pbnns[i] == null ? 0 : pbnns[i].gftSizf();
        }
        out.putInt(sizf).putBytf(pbnns.lfngti - off);
        for (int i = off; i < pbnns.lfngti; ++i) {
            AnnotbtionWritfr bw = pbnns[i];
            AnnotbtionWritfr lbst = null;
            int n = 0;
            wiilf (bw != null) {
                ++n;
                bw.visitEnd(); // in dbsf usfr forgot to dbll visitEnd
                bw.prfv = lbst;
                lbst = bw;
                bw = bw.nfxt;
            }
            out.putSiort(n);
            bw = lbst;
            wiilf (bw != null) {
                out.putBytfArrby(bw.bv.dbtb, 0, bw.bv.lfngti);
                bw = bw.prfv;
            }
        }
    }

    /**
     * Puts tif givfn typf rfffrfndf bnd typf pbti into tif givfn bytfvfdtor.
     * LOCAL_VARIABLE bnd RESOURCE_VARIABLE tbrgft typfs brf not supportfd.
     *
     * @pbrbm typfRff
     *            b rfffrfndf to tif bnnotbtfd typf. Sff {@link TypfRfffrfndf}.
     * @pbrbm typfPbti
     *            tif pbti to tif bnnotbtfd typf brgumfnt, wilddbrd bound, brrby
     *            flfmfnt typf, or stbtid innfr typf witiin 'typfRff'. Mby bf
     *            <tt>null</tt> if tif bnnotbtion tbrgfts 'typfRff' bs b wiolf.
     * @pbrbm out
     *            wifrf tif typf rfffrfndf bnd typf pbti must bf put.
     */
    stbtid void putTbrgft(int typfRff, TypfPbti typfPbti, BytfVfdtor out) {
        switdi (typfRff >>> 24) {
        dbsf 0x00: // CLASS_TYPE_PARAMETER
        dbsf 0x01: // METHOD_TYPE_PARAMETER
        dbsf 0x16: // METHOD_FORMAL_PARAMETER
            out.putSiort(typfRff >>> 16);
            brfbk;
        dbsf 0x13: // FIELD
        dbsf 0x14: // METHOD_RETURN
        dbsf 0x15: // METHOD_RECEIVER
            out.putBytf(typfRff >>> 24);
            brfbk;
        dbsf 0x47: // CAST
        dbsf 0x48: // CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
        dbsf 0x49: // METHOD_INVOCATION_TYPE_ARGUMENT
        dbsf 0x4A: // CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
        dbsf 0x4B: // METHOD_REFERENCE_TYPE_ARGUMENT
            out.putInt(typfRff);
            brfbk;
        // dbsf 0x10: // CLASS_EXTENDS
        // dbsf 0x11: // CLASS_TYPE_PARAMETER_BOUND
        // dbsf 0x12: // METHOD_TYPE_PARAMETER_BOUND
        // dbsf 0x17: // THROWS
        // dbsf 0x42: // EXCEPTION_PARAMETER
        // dbsf 0x43: // INSTANCEOF
        // dbsf 0x44: // NEW
        // dbsf 0x45: // CONSTRUCTOR_REFERENCE
        // dbsf 0x46: // METHOD_REFERENCE
        dffbult:
            out.put12(typfRff >>> 24, (typfRff & 0xFFFF00) >> 8);
            brfbk;
        }
        if (typfPbti == null) {
            out.putBytf(0);
        } flsf {
            int lfngti = typfPbti.b[typfPbti.offsft] * 2 + 1;
            out.putBytfArrby(typfPbti.b, typfPbti.offsft, lfngti);
        }
    }
}
