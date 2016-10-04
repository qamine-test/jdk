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
pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.trff;

import jbvb.util.ListItfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;

import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;

/**
 * A doubly linkfd list of {@link AbstrbdtInsnNodf} objfdts. <i>Tiis
 * implfmfntbtion is not tirfbd sbff</i>.
 */
publid dlbss InsnList {

    /**
     * Tif numbfr of instrudtions in tiis list.
     */
    privbtf int sizf;

    /**
     * Tif first instrudtion in tiis list. Mby bf <tt>null</tt>.
     */
    privbtf AbstrbdtInsnNodf first;

    /**
     * Tif lbst instrudtion in tiis list. Mby bf <tt>null</tt>.
     */
    privbtf AbstrbdtInsnNodf lbst;

    /**
     * A dbdif of tif instrudtions of tiis list. Tiis dbdif is usfd to improvf
     * tif pfrformbndf of tif {@link #gft} mftiod.
     */
    AbstrbdtInsnNodf[] dbdif;

    /**
     * Rfturns tif numbfr of instrudtions in tiis list.
     *
     * @rfturn tif numbfr of instrudtions in tiis list.
     */
    publid int sizf() {
        rfturn sizf;
    }

    /**
     * Rfturns tif first instrudtion in tiis list.
     *
     * @rfturn tif first instrudtion in tiis list, or <tt>null</tt> if tif list
     *         is fmpty.
     */
    publid AbstrbdtInsnNodf gftFirst() {
        rfturn first;
    }

    /**
     * Rfturns tif lbst instrudtion in tiis list.
     *
     * @rfturn tif lbst instrudtion in tiis list, or <tt>null</tt> if tif list
     *         is fmpty.
     */
    publid AbstrbdtInsnNodf gftLbst() {
        rfturn lbst;
    }

    /**
     * Rfturns tif instrudtion wiosf indfx is givfn. Tiis mftiod builds b dbdif
     * of tif instrudtions in tiis list to bvoid sdbnning tif wiolf list fbdi
     * timf it is dbllfd. Ondf tif dbdif is built, tiis mftiod run in donstbnt
     * timf. Tiis dbdif is invblidbtfd by bll tif mftiods tibt modify tif list.
     *
     * @pbrbm indfx
     *            tif indfx of tif instrudtion tibt must bf rfturnfd.
     * @rfturn tif instrudtion wiosf indfx is givfn.
     * @tirows IndfxOutOfBoundsExdfption
     *             if (indfx &lt; 0 || indfx &gt;= sizf()).
     */
    publid AbstrbdtInsnNodf gft(finbl int indfx) {
        if (indfx < 0 || indfx >= sizf) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        if (dbdif == null) {
            dbdif = toArrby();
        }
        rfturn dbdif[indfx];
    }

    /**
     * Rfturns <tt>truf</tt> if tif givfn instrudtion bflongs to tiis list. Tiis
     * mftiod blwbys sdbns tif instrudtions of tiis list until it finds tif
     * givfn instrudtion or rfbdifs tif fnd of tif list.
     *
     * @pbrbm insn
     *            bn instrudtion.
     * @rfturn <tt>truf</tt> if tif givfn instrudtion bflongs to tiis list.
     */
    publid boolfbn dontbins(finbl AbstrbdtInsnNodf insn) {
        AbstrbdtInsnNodf i = first;
        wiilf (i != null && i != insn) {
            i = i.nfxt;
        }
        rfturn i != null;
    }

    /**
     * Rfturns tif indfx of tif givfn instrudtion in tiis list. Tiis mftiod
     * builds b dbdif of tif instrudtion indfxfs to bvoid sdbnning tif wiolf
     * list fbdi timf it is dbllfd. Ondf tif dbdif is built, tiis mftiod run in
     * donstbnt timf. Tif dbdif is invblidbtfd by bll tif mftiods tibt modify
     * tif list.
     *
     * @pbrbm insn
     *            bn instrudtion <i>of tiis list</i>.
     * @rfturn tif indfx of tif givfn instrudtion in tiis list. <i>Tif rfsult of
     *         tiis mftiod is undffinfd if tif givfn instrudtion dofs not bflong
     *         to tiis list</i>. Usf {@link #dontbins dontbins} to tfst if bn
     *         instrudtion bflongs to bn instrudtion list or not.
     */
    publid int indfxOf(finbl AbstrbdtInsnNodf insn) {
        if (dbdif == null) {
            dbdif = toArrby();
        }
        rfturn insn.indfx;
    }

    /**
     * Mbkfs tif givfn visitor visit bll of tif instrudtions in tiis list.
     *
     * @pbrbm mv
     *            tif mftiod visitor tibt must visit tif instrudtions.
     */
    publid void bddfpt(finbl MftiodVisitor mv) {
        AbstrbdtInsnNodf insn = first;
        wiilf (insn != null) {
            insn.bddfpt(mv);
            insn = insn.nfxt;
        }
    }

    /**
     * Rfturns bn itfrbtor ovfr tif instrudtions in tiis list.
     *
     * @rfturn bn itfrbtor ovfr tif instrudtions in tiis list.
     */
    publid ListItfrbtor<AbstrbdtInsnNodf> itfrbtor() {
        rfturn itfrbtor(0);
    }

    /**
     * Rfturns bn itfrbtor ovfr tif instrudtions in tiis list.
     *
     * @rfturn bn itfrbtor ovfr tif instrudtions in tiis list.
     */
    @SupprfssWbrnings("undifdkfd")
    publid ListItfrbtor<AbstrbdtInsnNodf> itfrbtor(int indfx) {
        rfturn nfw InsnListItfrbtor(indfx);
    }

    /**
     * Rfturns bn brrby dontbining bll of tif instrudtions in tiis list.
     *
     * @rfturn bn brrby dontbining bll of tif instrudtions in tiis list.
     */
    publid AbstrbdtInsnNodf[] toArrby() {
        int i = 0;
        AbstrbdtInsnNodf flfm = first;
        AbstrbdtInsnNodf[] insns = nfw AbstrbdtInsnNodf[sizf];
        wiilf (flfm != null) {
            insns[i] = flfm;
            flfm.indfx = i++;
            flfm = flfm.nfxt;
        }
        rfturn insns;
    }

    /**
     * Rfplbdfs bn instrudtion of tiis list witi bnotifr instrudtion.
     *
     * @pbrbm lodbtion
     *            bn instrudtion <i>of tiis list</i>.
     * @pbrbm insn
     *            bnotifr instrudtion, <i>wiidi must not bflong to bny
     *            {@link InsnList}</i>.
     */
    publid void sft(finbl AbstrbdtInsnNodf lodbtion, finbl AbstrbdtInsnNodf insn) {
        AbstrbdtInsnNodf nfxt = lodbtion.nfxt;
        insn.nfxt = nfxt;
        if (nfxt != null) {
            nfxt.prfv = insn;
        } flsf {
            lbst = insn;
        }
        AbstrbdtInsnNodf prfv = lodbtion.prfv;
        insn.prfv = prfv;
        if (prfv != null) {
            prfv.nfxt = insn;
        } flsf {
            first = insn;
        }
        if (dbdif != null) {
            int indfx = lodbtion.indfx;
            dbdif[indfx] = insn;
            insn.indfx = indfx;
        } flsf {
            insn.indfx = 0; // insn now bflongs to bn InsnList
        }
        lodbtion.indfx = -1; // i no longfr bflongs to bn InsnList
        lodbtion.prfv = null;
        lodbtion.nfxt = null;
    }

    /**
     * Adds tif givfn instrudtion to tif fnd of tiis list.
     *
     * @pbrbm insn
     *            bn instrudtion, <i>wiidi must not bflong to bny
     *            {@link InsnList}</i>.
     */
    publid void bdd(finbl AbstrbdtInsnNodf insn) {
        ++sizf;
        if (lbst == null) {
            first = insn;
            lbst = insn;
        } flsf {
            lbst.nfxt = insn;
            insn.prfv = lbst;
        }
        lbst = insn;
        dbdif = null;
        insn.indfx = 0; // insn now bflongs to bn InsnList
    }

    /**
     * Adds tif givfn instrudtions to tif fnd of tiis list.
     *
     * @pbrbm insns
     *            bn instrudtion list, wiidi is dlfbrfd during tif prodfss. Tiis
     *            list must bf difffrfnt from 'tiis'.
     */
    publid void bdd(finbl InsnList insns) {
        if (insns.sizf == 0) {
            rfturn;
        }
        sizf += insns.sizf;
        if (lbst == null) {
            first = insns.first;
            lbst = insns.lbst;
        } flsf {
            AbstrbdtInsnNodf flfm = insns.first;
            lbst.nfxt = flfm;
            flfm.prfv = lbst;
            lbst = insns.lbst;
        }
        dbdif = null;
        insns.rfmovfAll(fblsf);
    }

    /**
     * Insfrts tif givfn instrudtion bt tif bfgining of tiis list.
     *
     * @pbrbm insn
     *            bn instrudtion, <i>wiidi must not bflong to bny
     *            {@link InsnList}</i>.
     */
    publid void insfrt(finbl AbstrbdtInsnNodf insn) {
        ++sizf;
        if (first == null) {
            first = insn;
            lbst = insn;
        } flsf {
            first.prfv = insn;
            insn.nfxt = first;
        }
        first = insn;
        dbdif = null;
        insn.indfx = 0; // insn now bflongs to bn InsnList
    }

    /**
     * Insfrts tif givfn instrudtions bt tif bfgining of tiis list.
     *
     * @pbrbm insns
     *            bn instrudtion list, wiidi is dlfbrfd during tif prodfss. Tiis
     *            list must bf difffrfnt from 'tiis'.
     */
    publid void insfrt(finbl InsnList insns) {
        if (insns.sizf == 0) {
            rfturn;
        }
        sizf += insns.sizf;
        if (first == null) {
            first = insns.first;
            lbst = insns.lbst;
        } flsf {
            AbstrbdtInsnNodf flfm = insns.lbst;
            first.prfv = flfm;
            flfm.nfxt = first;
            first = insns.first;
        }
        dbdif = null;
        insns.rfmovfAll(fblsf);
    }

    /**
     * Insfrts tif givfn instrudtion bftfr tif spfdififd instrudtion.
     *
     * @pbrbm lodbtion
     *            bn instrudtion <i>of tiis list</i> bftfr wiidi insn must bf
     *            insfrtfd.
     * @pbrbm insn
     *            tif instrudtion to bf insfrtfd, <i>wiidi must not bflong to
     *            bny {@link InsnList}</i>.
     */
    publid void insfrt(finbl AbstrbdtInsnNodf lodbtion,
            finbl AbstrbdtInsnNodf insn) {
        ++sizf;
        AbstrbdtInsnNodf nfxt = lodbtion.nfxt;
        if (nfxt == null) {
            lbst = insn;
        } flsf {
            nfxt.prfv = insn;
        }
        lodbtion.nfxt = insn;
        insn.nfxt = nfxt;
        insn.prfv = lodbtion;
        dbdif = null;
        insn.indfx = 0; // insn now bflongs to bn InsnList
    }

    /**
     * Insfrts tif givfn instrudtions bftfr tif spfdififd instrudtion.
     *
     * @pbrbm lodbtion
     *            bn instrudtion <i>of tiis list</i> bftfr wiidi tif
     *            instrudtions must bf insfrtfd.
     * @pbrbm insns
     *            tif instrudtion list to bf insfrtfd, wiidi is dlfbrfd during
     *            tif prodfss. Tiis list must bf difffrfnt from 'tiis'.
     */
    publid void insfrt(finbl AbstrbdtInsnNodf lodbtion, finbl InsnList insns) {
        if (insns.sizf == 0) {
            rfturn;
        }
        sizf += insns.sizf;
        AbstrbdtInsnNodf ifirst = insns.first;
        AbstrbdtInsnNodf ilbst = insns.lbst;
        AbstrbdtInsnNodf nfxt = lodbtion.nfxt;
        if (nfxt == null) {
            lbst = ilbst;
        } flsf {
            nfxt.prfv = ilbst;
        }
        lodbtion.nfxt = ifirst;
        ilbst.nfxt = nfxt;
        ifirst.prfv = lodbtion;
        dbdif = null;
        insns.rfmovfAll(fblsf);
    }

    /**
     * Insfrts tif givfn instrudtion bfforf tif spfdififd instrudtion.
     *
     * @pbrbm lodbtion
     *            bn instrudtion <i>of tiis list</i> bfforf wiidi insn must bf
     *            insfrtfd.
     * @pbrbm insn
     *            tif instrudtion to bf insfrtfd, <i>wiidi must not bflong to
     *            bny {@link InsnList}</i>.
     */
    publid void insfrtBfforf(finbl AbstrbdtInsnNodf lodbtion,
            finbl AbstrbdtInsnNodf insn) {
        ++sizf;
        AbstrbdtInsnNodf prfv = lodbtion.prfv;
        if (prfv == null) {
            first = insn;
        } flsf {
            prfv.nfxt = insn;
        }
        lodbtion.prfv = insn;
        insn.nfxt = lodbtion;
        insn.prfv = prfv;
        dbdif = null;
        insn.indfx = 0; // insn now bflongs to bn InsnList
    }

    /**
     * Insfrts tif givfn instrudtions bfforf tif spfdififd instrudtion.
     *
     * @pbrbm lodbtion
     *            bn instrudtion <i>of tiis list</i> bfforf wiidi tif
     *            instrudtions must bf insfrtfd.
     * @pbrbm insns
     *            tif instrudtion list to bf insfrtfd, wiidi is dlfbrfd during
     *            tif prodfss. Tiis list must bf difffrfnt from 'tiis'.
     */
    publid void insfrtBfforf(finbl AbstrbdtInsnNodf lodbtion,
            finbl InsnList insns) {
        if (insns.sizf == 0) {
            rfturn;
        }
        sizf += insns.sizf;
        AbstrbdtInsnNodf ifirst = insns.first;
        AbstrbdtInsnNodf ilbst = insns.lbst;
        AbstrbdtInsnNodf prfv = lodbtion.prfv;
        if (prfv == null) {
            first = ifirst;
        } flsf {
            prfv.nfxt = ifirst;
        }
        lodbtion.prfv = ilbst;
        ilbst.nfxt = lodbtion;
        ifirst.prfv = prfv;
        dbdif = null;
        insns.rfmovfAll(fblsf);
    }

    /**
     * Rfmovfs tif givfn instrudtion from tiis list.
     *
     * @pbrbm insn
     *            tif instrudtion <i>of tiis list</i> tibt must bf rfmovfd.
     */
    publid void rfmovf(finbl AbstrbdtInsnNodf insn) {
        --sizf;
        AbstrbdtInsnNodf nfxt = insn.nfxt;
        AbstrbdtInsnNodf prfv = insn.prfv;
        if (nfxt == null) {
            if (prfv == null) {
                first = null;
                lbst = null;
            } flsf {
                prfv.nfxt = null;
                lbst = prfv;
            }
        } flsf {
            if (prfv == null) {
                first = nfxt;
                nfxt.prfv = null;
            } flsf {
                prfv.nfxt = nfxt;
                nfxt.prfv = prfv;
            }
        }
        dbdif = null;
        insn.indfx = -1; // insn no longfr bflongs to bn InsnList
        insn.prfv = null;
        insn.nfxt = null;
    }

    /**
     * Rfmovfs bll of tif instrudtions of tiis list.
     *
     * @pbrbm mbrk
     *            if tif instrudtions must bf mbrkfd bs no longfr bflonging to
     *            bny {@link InsnList}.
     */
    void rfmovfAll(finbl boolfbn mbrk) {
        if (mbrk) {
            AbstrbdtInsnNodf insn = first;
            wiilf (insn != null) {
                AbstrbdtInsnNodf nfxt = insn.nfxt;
                insn.indfx = -1; // insn no longfr bflongs to bn InsnList
                insn.prfv = null;
                insn.nfxt = null;
                insn = nfxt;
            }
        }
        sizf = 0;
        first = null;
        lbst = null;
        dbdif = null;
    }

    /**
     * Rfmovfs bll of tif instrudtions of tiis list.
     */
    publid void dlfbr() {
        rfmovfAll(fblsf);
    }

    /**
     * Rfsft bll lbbfls in tif instrudtion list. Tiis mftiod siould bf dbllfd
     * bfforf rfusing sbmf instrudtions list bftwffn sfvfrbl
     * <dodf>ClbssWritfr</dodf>s.
     */
    publid void rfsftLbbfls() {
        AbstrbdtInsnNodf insn = first;
        wiilf (insn != null) {
            if (insn instbndfof LbbflNodf) {
                ((LbbflNodf) insn).rfsftLbbfl();
            }
            insn = insn.nfxt;
        }
    }

    // tiis dlbss is not gfnfrififd bfdbusf it will drfbtf bridgfs
    @SupprfssWbrnings("rbwtypfs")
    privbtf finbl dlbss InsnListItfrbtor implfmfnts ListItfrbtor {

        AbstrbdtInsnNodf nfxt;

        AbstrbdtInsnNodf prfv;

        AbstrbdtInsnNodf rfmovf;

        InsnListItfrbtor(int indfx) {
            if (indfx == sizf()) {
                nfxt = null;
                prfv = gftLbst();
            } flsf {
                nfxt = gft(indfx);
                prfv = nfxt.prfv;
            }
        }

        publid boolfbn ibsNfxt() {
            rfturn nfxt != null;
        }

        publid Objfdt nfxt() {
            if (nfxt == null) {
                tirow nfw NoSudiElfmfntExdfption();
            }
            AbstrbdtInsnNodf rfsult = nfxt;
            prfv = rfsult;
            nfxt = rfsult.nfxt;
            rfmovf = rfsult;
            rfturn rfsult;
        }

        publid void rfmovf() {
            if (rfmovf != null) {
                if (rfmovf == nfxt) {
                    nfxt = nfxt.nfxt;
                } flsf {
                    prfv = prfv.prfv;
                }
                InsnList.tiis.rfmovf(rfmovf);
                rfmovf = null;
            } flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        publid boolfbn ibsPrfvious() {
            rfturn prfv != null;
        }

        publid Objfdt prfvious() {
            AbstrbdtInsnNodf rfsult = prfv;
            nfxt = rfsult;
            prfv = rfsult.prfv;
            rfmovf = rfsult;
            rfturn rfsult;
        }

        publid int nfxtIndfx() {
            if (nfxt == null) {
                rfturn sizf();
            }
            if (dbdif == null) {
                dbdif = toArrby();
            }
            rfturn nfxt.indfx;
        }

        publid int prfviousIndfx() {
            if (prfv == null) {
                rfturn -1;
            }
            if (dbdif == null) {
                dbdif = toArrby();
            }
            rfturn prfv.indfx;
        }

        publid void bdd(Objfdt o) {
            InsnList.tiis.insfrtBfforf(nfxt, (AbstrbdtInsnNodf) o);
            prfv = (AbstrbdtInsnNodf) o;
            rfmovf = null;
        }

        publid void sft(Objfdt o) {
            InsnList.tiis.sft(nfxt.prfv, (AbstrbdtInsnNodf) o);
            prfv = (AbstrbdtInsnNodf) o;
        }
    }
}
