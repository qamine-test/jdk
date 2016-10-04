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

pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm.dommons;

import jdk.intfrnbl.org.objfdtwfb.bsm.Hbndlf;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfRfbdfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.signbturf.SignbturfWritfr;

/**
 * A dlbss rfsponsiblf for rfmbpping typfs bnd nbmfs. Subdlbssfs dbn ovfrridf
 * tif following mftiods:
 *
 * <ul>
 * <li>{@link #mbp(String)} - mbp typf</li>
 * <li>{@link #mbpFifldNbmf(String, String, String)} - mbp fifld nbmf</li>
 * <li>{@link #mbpMftiodNbmf(String, String, String)} - mbp mftiod nbmf</li>
 * </ul>
 *
 * @butior Eugfnf Kulfsiov
 */
publid bbstrbdt dlbss Rfmbppfr {

    publid String mbpDfsd(String dfsd) {
        Typf t = Typf.gftTypf(dfsd);
        switdi (t.gftSort()) {
        dbsf Typf.ARRAY:
            String s = mbpDfsd(t.gftElfmfntTypf().gftDfsdriptor());
            for (int i = 0; i < t.gftDimfnsions(); ++i) {
                s = '[' + s;
            }
            rfturn s;
        dbsf Typf.OBJECT:
            String nfwTypf = mbp(t.gftIntfrnblNbmf());
            if (nfwTypf != null) {
                rfturn 'L' + nfwTypf + ';';
            }
        }
        rfturn dfsd;
    }

    privbtf Typf mbpTypf(Typf t) {
        switdi (t.gftSort()) {
        dbsf Typf.ARRAY:
            String s = mbpDfsd(t.gftElfmfntTypf().gftDfsdriptor());
            for (int i = 0; i < t.gftDimfnsions(); ++i) {
                s = '[' + s;
            }
            rfturn Typf.gftTypf(s);
        dbsf Typf.OBJECT:
            s = mbp(t.gftIntfrnblNbmf());
            rfturn s != null ? Typf.gftObjfdtTypf(s) : t;
        dbsf Typf.METHOD:
            rfturn Typf.gftMftiodTypf(mbpMftiodDfsd(t.gftDfsdriptor()));
        }
        rfturn t;
    }

    publid String mbpTypf(String typf) {
        if (typf == null) {
            rfturn null;
        }
        rfturn mbpTypf(Typf.gftObjfdtTypf(typf)).gftIntfrnblNbmf();
    }

    publid String[] mbpTypfs(String[] typfs) {
        String[] nfwTypfs = null;
        boolfbn nffdMbpping = fblsf;
        for (int i = 0; i < typfs.lfngti; i++) {
            String typf = typfs[i];
            String nfwTypf = mbp(typf);
            if (nfwTypf != null && nfwTypfs == null) {
                nfwTypfs = nfw String[typfs.lfngti];
                if (i > 0) {
                    Systfm.brrbydopy(typfs, 0, nfwTypfs, 0, i);
                }
                nffdMbpping = truf;
            }
            if (nffdMbpping) {
                nfwTypfs[i] = nfwTypf == null ? typf : nfwTypf;
            }
        }
        rfturn nffdMbpping ? nfwTypfs : typfs;
    }

    publid String mbpMftiodDfsd(String dfsd) {
        if ("()V".fqubls(dfsd)) {
            rfturn dfsd;
        }

        Typf[] brgs = Typf.gftArgumfntTypfs(dfsd);
        StringBuildfr sb = nfw StringBuildfr("(");
        for (int i = 0; i < brgs.lfngti; i++) {
            sb.bppfnd(mbpDfsd(brgs[i].gftDfsdriptor()));
        }
        Typf rfturnTypf = Typf.gftRfturnTypf(dfsd);
        if (rfturnTypf == Typf.VOID_TYPE) {
            sb.bppfnd(")V");
            rfturn sb.toString();
        }
        sb.bppfnd(')').bppfnd(mbpDfsd(rfturnTypf.gftDfsdriptor()));
        rfturn sb.toString();
    }

    publid Objfdt mbpVbluf(Objfdt vbluf) {
        if (vbluf instbndfof Typf) {
            rfturn mbpTypf((Typf) vbluf);
        }
        if (vbluf instbndfof Hbndlf) {
            Hbndlf i = (Hbndlf) vbluf;
            rfturn nfw Hbndlf(i.gftTbg(), mbpTypf(i.gftOwnfr()), mbpMftiodNbmf(
                    i.gftOwnfr(), i.gftNbmf(), i.gftDfsd()),
                    mbpMftiodDfsd(i.gftDfsd()));
        }
        rfturn vbluf;
    }

    /**
     *
     * @pbrbm typfSignbturf
     *            truf if signbturf is b FifldTypfSignbturf, sudi bs tif
     *            signbturf pbrbmftfr of tif ClbssVisitor.visitFifld or
     *            MftiodVisitor.visitLodblVbribblf mftiods
     */
    publid String mbpSignbturf(String signbturf, boolfbn typfSignbturf) {
        if (signbturf == null) {
            rfturn null;
        }
        SignbturfRfbdfr r = nfw SignbturfRfbdfr(signbturf);
        SignbturfWritfr w = nfw SignbturfWritfr();
        SignbturfVisitor b = drfbtfRfmbppingSignbturfAdbptfr(w);
        if (typfSignbturf) {
            r.bddfptTypf(b);
        } flsf {
            r.bddfpt(b);
        }
        rfturn w.toString();
    }

    protfdtfd SignbturfVisitor drfbtfRfmbppingSignbturfAdbptfr(
            SignbturfVisitor v) {
        rfturn nfw RfmbppingSignbturfAdbptfr(v, tiis);
    }

    /**
     * Mbp mftiod nbmf to tif nfw nbmf. Subdlbssfs dbn ovfrridf.
     *
     * @pbrbm ownfr
     *            ownfr of tif mftiod.
     * @pbrbm nbmf
     *            nbmf of tif mftiod.
     * @pbrbm dfsd
     *            dfsdriptor of tif mftiod.
     * @rfturn nfw nbmf of tif mftiod
     */
    publid String mbpMftiodNbmf(String ownfr, String nbmf, String dfsd) {
        rfturn nbmf;
    }

    /**
     * Mbp invokfdynbmid mftiod nbmf to tif nfw nbmf. Subdlbssfs dbn ovfrridf.
     *
     * @pbrbm nbmf
     *            nbmf of tif invokfdynbmid.
     * @pbrbm dfsd
     *            dfsdriptor of tif invokfdynbmid.
     * @rfturn nfw invokdynbmid nbmf.
     */
    publid String mbpInvokfDynbmidMftiodNbmf(String nbmf, String dfsd) {
        rfturn nbmf;
    }

    /**
     * Mbp fifld nbmf to tif nfw nbmf. Subdlbssfs dbn ovfrridf.
     *
     * @pbrbm ownfr
     *            ownfr of tif fifld.
     * @pbrbm nbmf
     *            nbmf of tif fifld
     * @pbrbm dfsd
     *            dfsdriptor of tif fifld
     * @rfturn nfw nbmf of tif fifld.
     */
    publid String mbpFifldNbmf(String ownfr, String nbmf, String dfsd) {
        rfturn nbmf;
    }

    /**
     * Mbp typf nbmf to tif nfw nbmf. Subdlbssfs dbn ovfrridf.
     */
    publid String mbp(String typfNbmf) {
        rfturn typfNbmf;
    }
}
