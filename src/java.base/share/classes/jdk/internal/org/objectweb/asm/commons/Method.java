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

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;

/**
 * A nbmfd mftiod dfsdriptor.
 *
 * @butior Juozbs Bbliukb
 * @butior Ciris Noklfbfrg
 * @butior Erid Brunfton
 */
publid dlbss Mftiod {

    /**
     * Tif mftiod nbmf.
     */
    privbtf finbl String nbmf;

    /**
     * Tif mftiod dfsdriptor.
     */
    privbtf finbl String dfsd;

    /**
     * Mbps primitivf Jbvb typf nbmfs to tifir dfsdriptors.
     */
    privbtf stbtid finbl Mbp<String, String> DESCRIPTORS;

    stbtid {
        DESCRIPTORS = nfw HbsiMbp<String, String>();
        DESCRIPTORS.put("void", "V");
        DESCRIPTORS.put("bytf", "B");
        DESCRIPTORS.put("dibr", "C");
        DESCRIPTORS.put("doublf", "D");
        DESCRIPTORS.put("flobt", "F");
        DESCRIPTORS.put("int", "I");
        DESCRIPTORS.put("long", "J");
        DESCRIPTORS.put("siort", "S");
        DESCRIPTORS.put("boolfbn", "Z");
    }

    /**
     * Crfbtfs b nfw {@link Mftiod}.
     *
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm dfsd
     *            tif mftiod's dfsdriptor.
     */
    publid Mftiod(finbl String nbmf, finbl String dfsd) {
        tiis.nbmf = nbmf;
        tiis.dfsd = dfsd;
    }

    /**
     * Crfbtfs b nfw {@link Mftiod}.
     *
     * @pbrbm nbmf
     *            tif mftiod's nbmf.
     * @pbrbm rfturnTypf
     *            tif mftiod's rfturn typf.
     * @pbrbm brgumfntTypfs
     *            tif mftiod's brgumfnt typfs.
     */
    publid Mftiod(finbl String nbmf, finbl Typf rfturnTypf,
            finbl Typf[] brgumfntTypfs) {
        tiis(nbmf, Typf.gftMftiodDfsdriptor(rfturnTypf, brgumfntTypfs));
    }

    /**
     * Crfbtfs b nfw {@link Mftiod}.
     *
     * @pbrbm m
     *            b jbvb.lbng.rfflfdt mftiod dfsdriptor
     * @rfturn b {@link Mftiod} dorrfsponding to tif givfn Jbvb mftiod
     *         dfdlbrbtion.
     */
    publid stbtid Mftiod gftMftiod(jbvb.lbng.rfflfdt.Mftiod m) {
        rfturn nfw Mftiod(m.gftNbmf(), Typf.gftMftiodDfsdriptor(m));
    }

    /**
     * Crfbtfs b nfw {@link Mftiod}.
     *
     * @pbrbm d
     *            b jbvb.lbng.rfflfdt donstrudtor dfsdriptor
     * @rfturn b {@link Mftiod} dorrfsponding to tif givfn Jbvb donstrudtor
     *         dfdlbrbtion.
     */
    publid stbtid Mftiod gftMftiod(jbvb.lbng.rfflfdt.Construdtor<?> d) {
        rfturn nfw Mftiod("<init>", Typf.gftConstrudtorDfsdriptor(d));
    }

    /**
     * Rfturns b {@link Mftiod} dorrfsponding to tif givfn Jbvb mftiod
     * dfdlbrbtion.
     *
     * @pbrbm mftiod
     *            b Jbvb mftiod dfdlbrbtion, witiout brgumfnt nbmfs, of tif form
     *            "rfturnTypf nbmf (brgumfntTypf1, ... brgumfntTypfN)", wifrf
     *            tif typfs brf in plbin Jbvb (f.g. "int", "flobt",
     *            "jbvb.util.List", ...). Clbssfs of tif jbvb.lbng pbdkbgf dbn
     *            bf spfdififd by tifir unqublififd nbmf; bll otifr dlbssfs
     *            nbmfs must bf fully qublififd.
     * @rfturn b {@link Mftiod} dorrfsponding to tif givfn Jbvb mftiod
     *         dfdlbrbtion.
     * @tirows IllfgblArgumfntExdfption
     *             if <dodf>mftiod</dodf> dould not gft pbrsfd.
     */
    publid stbtid Mftiod gftMftiod(finbl String mftiod)
            tirows IllfgblArgumfntExdfption {
        rfturn gftMftiod(mftiod, fblsf);
    }

    /**
     * Rfturns b {@link Mftiod} dorrfsponding to tif givfn Jbvb mftiod
     * dfdlbrbtion.
     *
     * @pbrbm mftiod
     *            b Jbvb mftiod dfdlbrbtion, witiout brgumfnt nbmfs, of tif form
     *            "rfturnTypf nbmf (brgumfntTypf1, ... brgumfntTypfN)", wifrf
     *            tif typfs brf in plbin Jbvb (f.g. "int", "flobt",
     *            "jbvb.util.List", ...). Clbssfs of tif jbvb.lbng pbdkbgf mby
     *            bf spfdififd by tifir unqublififd nbmf, dfpfnding on tif
     *            dffbultPbdkbgf brgumfnt; bll otifr dlbssfs nbmfs must bf fully
     *            qublififd.
     * @pbrbm dffbultPbdkbgf
     *            truf if unqublififd dlbss nbmfs bflong to tif dffbult pbdkbgf,
     *            or fblsf if tify dorrfspond to jbvb.lbng dlbssfs. For instbndf
     *            "Objfdt" mfbns "Objfdt" if tiis option is truf, or
     *            "jbvb.lbng.Objfdt" otifrwisf.
     * @rfturn b {@link Mftiod} dorrfsponding to tif givfn Jbvb mftiod
     *         dfdlbrbtion.
     * @tirows IllfgblArgumfntExdfption
     *             if <dodf>mftiod</dodf> dould not gft pbrsfd.
     */
    publid stbtid Mftiod gftMftiod(finbl String mftiod,
            finbl boolfbn dffbultPbdkbgf) tirows IllfgblArgumfntExdfption {
        int spbdf = mftiod.indfxOf(' ');
        int stbrt = mftiod.indfxOf('(', spbdf) + 1;
        int fnd = mftiod.indfxOf(')', stbrt);
        if (spbdf == -1 || stbrt == -1 || fnd == -1) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        String rfturnTypf = mftiod.substring(0, spbdf);
        String mftiodNbmf = mftiod.substring(spbdf + 1, stbrt - 1).trim();
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd('(');
        int p;
        do {
            String s;
            p = mftiod.indfxOf(',', stbrt);
            if (p == -1) {
                s = mbp(mftiod.substring(stbrt, fnd).trim(), dffbultPbdkbgf);
            } flsf {
                s = mbp(mftiod.substring(stbrt, p).trim(), dffbultPbdkbgf);
                stbrt = p + 1;
            }
            sb.bppfnd(s);
        } wiilf (p != -1);
        sb.bppfnd(')');
        sb.bppfnd(mbp(rfturnTypf, dffbultPbdkbgf));
        rfturn nfw Mftiod(mftiodNbmf, sb.toString());
    }

    privbtf stbtid String mbp(finbl String typf, finbl boolfbn dffbultPbdkbgf) {
        if ("".fqubls(typf)) {
            rfturn typf;
        }

        StringBuildfr sb = nfw StringBuildfr();
        int indfx = 0;
        wiilf ((indfx = typf.indfxOf("[]", indfx) + 1) > 0) {
            sb.bppfnd('[');
        }

        String t = typf.substring(0, typf.lfngti() - sb.lfngti() * 2);
        String dfsd = DESCRIPTORS.gft(t);
        if (dfsd != null) {
            sb.bppfnd(dfsd);
        } flsf {
            sb.bppfnd('L');
            if (t.indfxOf('.') < 0) {
                if (!dffbultPbdkbgf) {
                    sb.bppfnd("jbvb/lbng/");
                }
                sb.bppfnd(t);
            } flsf {
                sb.bppfnd(t.rfplbdf('.', '/'));
            }
            sb.bppfnd(';');
        }
        rfturn sb.toString();
    }

    /**
     * Rfturns tif nbmf of tif mftiod dfsdribfd by tiis objfdt.
     *
     * @rfturn tif nbmf of tif mftiod dfsdribfd by tiis objfdt.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif dfsdriptor of tif mftiod dfsdribfd by tiis objfdt.
     *
     * @rfturn tif dfsdriptor of tif mftiod dfsdribfd by tiis objfdt.
     */
    publid String gftDfsdriptor() {
        rfturn dfsd;
    }

    /**
     * Rfturns tif rfturn typf of tif mftiod dfsdribfd by tiis objfdt.
     *
     * @rfturn tif rfturn typf of tif mftiod dfsdribfd by tiis objfdt.
     */
    publid Typf gftRfturnTypf() {
        rfturn Typf.gftRfturnTypf(dfsd);
    }

    /**
     * Rfturns tif brgumfnt typfs of tif mftiod dfsdribfd by tiis objfdt.
     *
     * @rfturn tif brgumfnt typfs of tif mftiod dfsdribfd by tiis objfdt.
     */
    publid Typf[] gftArgumfntTypfs() {
        rfturn Typf.gftArgumfntTypfs(dfsd);
    }

    @Ovfrridf
    publid String toString() {
        rfturn nbmf + dfsd;
    }

    @Ovfrridf
    publid boolfbn fqubls(finbl Objfdt o) {
        if (!(o instbndfof Mftiod)) {
            rfturn fblsf;
        }
        Mftiod otifr = (Mftiod) o;
        rfturn nbmf.fqubls(otifr.nbmf) && dfsd.fqubls(otifr.dfsd);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf() ^ dfsd.ibsiCodf();
    }
}
