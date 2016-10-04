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
 * An {@link FifldVisitor} tibt gfnfrbtfs Jbvb fiflds in bytfdodf form.
 *
 * @butior Erid Brunfton
 */
finbl dlbss FifldWritfr fxtfnds FifldVisitor {

    /**
     * Tif dlbss writfr to wiidi tiis fifld must bf bddfd.
     */
    privbtf finbl ClbssWritfr dw;

    /**
     * Addfss flbgs of tiis fifld.
     */
    privbtf finbl int bddfss;

    /**
     * Tif indfx of tif donstbnt pool itfm tibt dontbins tif nbmf of tiis
     * mftiod.
     */
    privbtf finbl int nbmf;

    /**
     * Tif indfx of tif donstbnt pool itfm tibt dontbins tif dfsdriptor of tiis
     * fifld.
     */
    privbtf finbl int dfsd;

    /**
     * Tif indfx of tif donstbnt pool itfm tibt dontbins tif signbturf of tiis
     * fifld.
     */
    privbtf int signbturf;

    /**
     * Tif indfx of tif donstbnt pool itfm tibt dontbins tif donstbnt vbluf of
     * tiis fifld.
     */
    privbtf int vbluf;

    /**
     * Tif runtimf visiblf bnnotbtions of tiis fifld. Mby bf <tt>null</tt>.
     */
    privbtf AnnotbtionWritfr bnns;

    /**
     * Tif runtimf invisiblf bnnotbtions of tiis fifld. Mby bf <tt>null</tt>.
     */
    privbtf AnnotbtionWritfr ibnns;

    /**
     * Tif runtimf visiblf typf bnnotbtions of tiis fifld. Mby bf <tt>null</tt>.
     */
    privbtf AnnotbtionWritfr tbnns;

    /**
     * Tif runtimf invisiblf typf bnnotbtions of tiis fifld. Mby bf
     * <tt>null</tt>.
     */
    privbtf AnnotbtionWritfr itbnns;

    /**
     * Tif non stbndbrd bttributfs of tiis fifld. Mby bf <tt>null</tt>.
     */
    privbtf Attributf bttrs;

    // ------------------------------------------------------------------------
    // Construdtor
    // ------------------------------------------------------------------------

    /**
     * Construdts b nfw {@link FifldWritfr}.
     *
     * @pbrbm dw
     *            tif dlbss writfr to wiidi tiis fifld must bf bddfd.
     * @pbrbm bddfss
     *            tif fifld's bddfss flbgs (sff {@link Opdodfs}).
     * @pbrbm nbmf
     *            tif fifld's nbmf.
     * @pbrbm dfsd
     *            tif fifld's dfsdriptor (sff {@link Typf}).
     * @pbrbm signbturf
     *            tif fifld's signbturf. Mby bf <tt>null</tt>.
     * @pbrbm vbluf
     *            tif fifld's donstbnt vbluf. Mby bf <tt>null</tt>.
     */
    FifldWritfr(finbl ClbssWritfr dw, finbl int bddfss, finbl String nbmf,
            finbl String dfsd, finbl String signbturf, finbl Objfdt vbluf) {
        supfr(Opdodfs.ASM5);
        if (dw.firstFifld == null) {
            dw.firstFifld = tiis;
        } flsf {
            dw.lbstFifld.fv = tiis;
        }
        dw.lbstFifld = tiis;
        tiis.dw = dw;
        tiis.bddfss = bddfss;
        tiis.nbmf = dw.nfwUTF8(nbmf);
        tiis.dfsd = dw.nfwUTF8(dfsd);
        if (ClbssRfbdfr.SIGNATURES && signbturf != null) {
            tiis.signbturf = dw.nfwUTF8(signbturf);
        }
        if (vbluf != null) {
            tiis.vbluf = dw.nfwConstItfm(vbluf).indfx;
        }
    }

    // ------------------------------------------------------------------------
    // Implfmfntbtion of tif FifldVisitor bbstrbdt dlbss
    // ------------------------------------------------------------------------

    @Ovfrridf
    publid AnnotbtionVisitor visitAnnotbtion(finbl String dfsd,
            finbl boolfbn visiblf) {
        if (!ClbssRfbdfr.ANNOTATIONS) {
            rfturn null;
        }
        BytfVfdtor bv = nfw BytfVfdtor();
        // writf typf, bnd rfsfrvf spbdf for vblufs dount
        bv.putSiort(dw.nfwUTF8(dfsd)).putSiort(0);
        AnnotbtionWritfr bw = nfw AnnotbtionWritfr(dw, truf, bv, bv, 2);
        if (visiblf) {
            bw.nfxt = bnns;
            bnns = bw;
        } flsf {
            bw.nfxt = ibnns;
            ibnns = bw;
        }
        rfturn bw;
    }

    @Ovfrridf
    publid AnnotbtionVisitor visitTypfAnnotbtion(finbl int typfRff,
            finbl TypfPbti typfPbti, finbl String dfsd, finbl boolfbn visiblf) {
        if (!ClbssRfbdfr.ANNOTATIONS) {
            rfturn null;
        }
        BytfVfdtor bv = nfw BytfVfdtor();
        // writf tbrgft_typf bnd tbrgft_info
        AnnotbtionWritfr.putTbrgft(typfRff, typfPbti, bv);
        // writf typf, bnd rfsfrvf spbdf for vblufs dount
        bv.putSiort(dw.nfwUTF8(dfsd)).putSiort(0);
        AnnotbtionWritfr bw = nfw AnnotbtionWritfr(dw, truf, bv, bv,
                bv.lfngti - 2);
        if (visiblf) {
            bw.nfxt = tbnns;
            tbnns = bw;
        } flsf {
            bw.nfxt = itbnns;
            itbnns = bw;
        }
        rfturn bw;
    }

    @Ovfrridf
    publid void visitAttributf(finbl Attributf bttr) {
        bttr.nfxt = bttrs;
        bttrs = bttr;
    }

    @Ovfrridf
    publid void visitEnd() {
    }

    // ------------------------------------------------------------------------
    // Utility mftiods
    // ------------------------------------------------------------------------

    /**
     * Rfturns tif sizf of tiis fifld.
     *
     * @rfturn tif sizf of tiis fifld.
     */
    int gftSizf() {
        int sizf = 8;
        if (vbluf != 0) {
            dw.nfwUTF8("ConstbntVbluf");
            sizf += 8;
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            if ((dw.vfrsion & 0xFFFF) < Opdodfs.V1_5
                    || (bddfss & ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                dw.nfwUTF8("Syntiftid");
                sizf += 6;
            }
        }
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            dw.nfwUTF8("Dfprfdbtfd");
            sizf += 6;
        }
        if (ClbssRfbdfr.SIGNATURES && signbturf != 0) {
            dw.nfwUTF8("Signbturf");
            sizf += 8;
        }
        if (ClbssRfbdfr.ANNOTATIONS && bnns != null) {
            dw.nfwUTF8("RuntimfVisiblfAnnotbtions");
            sizf += 8 + bnns.gftSizf();
        }
        if (ClbssRfbdfr.ANNOTATIONS && ibnns != null) {
            dw.nfwUTF8("RuntimfInvisiblfAnnotbtions");
            sizf += 8 + ibnns.gftSizf();
        }
        if (ClbssRfbdfr.ANNOTATIONS && tbnns != null) {
            dw.nfwUTF8("RuntimfVisiblfTypfAnnotbtions");
            sizf += 8 + tbnns.gftSizf();
        }
        if (ClbssRfbdfr.ANNOTATIONS && itbnns != null) {
            dw.nfwUTF8("RuntimfInvisiblfTypfAnnotbtions");
            sizf += 8 + itbnns.gftSizf();
        }
        if (bttrs != null) {
            sizf += bttrs.gftSizf(dw, null, 0, -1, -1);
        }
        rfturn sizf;
    }

    /**
     * Puts tif dontfnt of tiis fifld into tif givfn bytf vfdtor.
     *
     * @pbrbm out
     *            wifrf tif dontfnt of tiis fifld must bf put.
     */
    void put(finbl BytfVfdtor out) {
        finbl int FACTOR = ClbssWritfr.TO_ACC_SYNTHETIC;
        int mbsk = Opdodfs.ACC_DEPRECATED | ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE
                | ((bddfss & ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE) / FACTOR);
        out.putSiort(bddfss & ~mbsk).putSiort(nbmf).putSiort(dfsd);
        int bttributfCount = 0;
        if (vbluf != 0) {
            ++bttributfCount;
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            if ((dw.vfrsion & 0xFFFF) < Opdodfs.V1_5
                    || (bddfss & ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                ++bttributfCount;
            }
        }
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            ++bttributfCount;
        }
        if (ClbssRfbdfr.SIGNATURES && signbturf != 0) {
            ++bttributfCount;
        }
        if (ClbssRfbdfr.ANNOTATIONS && bnns != null) {
            ++bttributfCount;
        }
        if (ClbssRfbdfr.ANNOTATIONS && ibnns != null) {
            ++bttributfCount;
        }
        if (ClbssRfbdfr.ANNOTATIONS && tbnns != null) {
            ++bttributfCount;
        }
        if (ClbssRfbdfr.ANNOTATIONS && itbnns != null) {
            ++bttributfCount;
        }
        if (bttrs != null) {
            bttributfCount += bttrs.gftCount();
        }
        out.putSiort(bttributfCount);
        if (vbluf != 0) {
            out.putSiort(dw.nfwUTF8("ConstbntVbluf"));
            out.putInt(2).putSiort(vbluf);
        }
        if ((bddfss & Opdodfs.ACC_SYNTHETIC) != 0) {
            if ((dw.vfrsion & 0xFFFF) < Opdodfs.V1_5
                    || (bddfss & ClbssWritfr.ACC_SYNTHETIC_ATTRIBUTE) != 0) {
                out.putSiort(dw.nfwUTF8("Syntiftid")).putInt(0);
            }
        }
        if ((bddfss & Opdodfs.ACC_DEPRECATED) != 0) {
            out.putSiort(dw.nfwUTF8("Dfprfdbtfd")).putInt(0);
        }
        if (ClbssRfbdfr.SIGNATURES && signbturf != 0) {
            out.putSiort(dw.nfwUTF8("Signbturf"));
            out.putInt(2).putSiort(signbturf);
        }
        if (ClbssRfbdfr.ANNOTATIONS && bnns != null) {
            out.putSiort(dw.nfwUTF8("RuntimfVisiblfAnnotbtions"));
            bnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && ibnns != null) {
            out.putSiort(dw.nfwUTF8("RuntimfInvisiblfAnnotbtions"));
            ibnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && tbnns != null) {
            out.putSiort(dw.nfwUTF8("RuntimfVisiblfTypfAnnotbtions"));
            tbnns.put(out);
        }
        if (ClbssRfbdfr.ANNOTATIONS && itbnns != null) {
            out.putSiort(dw.nfwUTF8("RuntimfInvisiblfTypfAnnotbtions"));
            itbnns.put(out);
        }
        if (bttrs != null) {
            bttrs.put(dw, null, 0, -1, -1, out);
        }
    }
}
