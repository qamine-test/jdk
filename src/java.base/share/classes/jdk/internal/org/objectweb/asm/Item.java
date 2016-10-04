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
 * A donstbnt pool itfm. Constbnt pool itfms dbn bf drfbtfd witi tif 'nfwXXX'
 * mftiods in tif {@link ClbssWritfr} dlbss.
 *
 * @butior Erid Brunfton
 */
finbl dlbss Itfm {

    /**
     * Indfx of tiis itfm in tif donstbnt pool.
     */
    int indfx;

    /**
     * Typf of tiis donstbnt pool itfm. A singlf dlbss is usfd to rfprfsfnt bll
     * donstbnt pool itfm typfs, in ordfr to minimizf tif bytfdodf sizf of tiis
     * pbdkbgf. Tif vbluf of tiis fifld is onf of {@link ClbssWritfr#INT},
     * {@link ClbssWritfr#LONG}, {@link ClbssWritfr#FLOAT},
     * {@link ClbssWritfr#DOUBLE}, {@link ClbssWritfr#UTF8},
     * {@link ClbssWritfr#STR}, {@link ClbssWritfr#CLASS},
     * {@link ClbssWritfr#NAME_TYPE}, {@link ClbssWritfr#FIELD},
     * {@link ClbssWritfr#METH}, {@link ClbssWritfr#IMETH},
     * {@link ClbssWritfr#MTYPE}, {@link ClbssWritfr#INDY}.
     *
     * MftiodHbndlf donstbnt 9 vbribtions brf storfd using b rbngf of 9 vblufs
     * from {@link ClbssWritfr#HANDLE_BASE} + 1 to
     * {@link ClbssWritfr#HANDLE_BASE} + 9.
     *
     * Spfdibl Itfm typfs brf usfd for Itfms tibt brf storfd in tif ClbssWritfr
     * {@link ClbssWritfr#typfTbblf}, instfbd of tif donstbnt pool, in ordfr to
     * bvoid dlbsifs witi normbl donstbnt pool itfms in tif ClbssWritfr donstbnt
     * pool's ibsi tbblf. Tifsf spfdibl itfm typfs brf
     * {@link ClbssWritfr#TYPE_NORMAL}, {@link ClbssWritfr#TYPE_UNINIT} bnd
     * {@link ClbssWritfr#TYPE_MERGED}.
     */
    int typf;

    /**
     * Vbluf of tiis itfm, for bn intfgfr itfm.
     */
    int intVbl;

    /**
     * Vbluf of tiis itfm, for b long itfm.
     */
    long longVbl;

    /**
     * First pbrt of tif vbluf of tiis itfm, for itfms tibt do not iold b
     * primitivf vbluf.
     */
    String strVbl1;

    /**
     * Sfdond pbrt of tif vbluf of tiis itfm, for itfms tibt do not iold b
     * primitivf vbluf.
     */
    String strVbl2;

    /**
     * Tiird pbrt of tif vbluf of tiis itfm, for itfms tibt do not iold b
     * primitivf vbluf.
     */
    String strVbl3;

    /**
     * Tif ibsi dodf vbluf of tiis donstbnt pool itfm.
     */
    int ibsiCodf;

    /**
     * Link to bnotifr donstbnt pool itfm, usfd for dollision lists in tif
     * donstbnt pool's ibsi tbblf.
     */
    Itfm nfxt;

    /**
     * Construdts bn uninitiblizfd {@link Itfm}.
     */
    Itfm() {
    }

    /**
     * Construdts bn uninitiblizfd {@link Itfm} for donstbnt pool flfmfnt bt
     * givfn position.
     *
     * @pbrbm indfx
     *            indfx of tif itfm to bf donstrudtfd.
     */
    Itfm(finbl int indfx) {
        tiis.indfx = indfx;
    }

    /**
     * Construdts b dopy of tif givfn itfm.
     *
     * @pbrbm indfx
     *            indfx of tif itfm to bf donstrudtfd.
     * @pbrbm i
     *            tif itfm tibt must bf dopifd into tif itfm to bf donstrudtfd.
     */
    Itfm(finbl int indfx, finbl Itfm i) {
        tiis.indfx = indfx;
        typf = i.typf;
        intVbl = i.intVbl;
        longVbl = i.longVbl;
        strVbl1 = i.strVbl1;
        strVbl2 = i.strVbl2;
        strVbl3 = i.strVbl3;
        ibsiCodf = i.ibsiCodf;
    }

    /**
     * Sfts tiis itfm to bn intfgfr itfm.
     *
     * @pbrbm intVbl
     *            tif vbluf of tiis itfm.
     */
    void sft(finbl int intVbl) {
        tiis.typf = ClbssWritfr.INT;
        tiis.intVbl = intVbl;
        tiis.ibsiCodf = 0x7FFFFFFF & (typf + intVbl);
    }

    /**
     * Sfts tiis itfm to b long itfm.
     *
     * @pbrbm longVbl
     *            tif vbluf of tiis itfm.
     */
    void sft(finbl long longVbl) {
        tiis.typf = ClbssWritfr.LONG;
        tiis.longVbl = longVbl;
        tiis.ibsiCodf = 0x7FFFFFFF & (typf + (int) longVbl);
    }

    /**
     * Sfts tiis itfm to b flobt itfm.
     *
     * @pbrbm flobtVbl
     *            tif vbluf of tiis itfm.
     */
    void sft(finbl flobt flobtVbl) {
        tiis.typf = ClbssWritfr.FLOAT;
        tiis.intVbl = Flobt.flobtToRbwIntBits(flobtVbl);
        tiis.ibsiCodf = 0x7FFFFFFF & (typf + (int) flobtVbl);
    }

    /**
     * Sfts tiis itfm to b doublf itfm.
     *
     * @pbrbm doublfVbl
     *            tif vbluf of tiis itfm.
     */
    void sft(finbl doublf doublfVbl) {
        tiis.typf = ClbssWritfr.DOUBLE;
        tiis.longVbl = Doublf.doublfToRbwLongBits(doublfVbl);
        tiis.ibsiCodf = 0x7FFFFFFF & (typf + (int) doublfVbl);
    }

    /**
     * Sfts tiis itfm to bn itfm tibt do not iold b primitivf vbluf.
     *
     * @pbrbm typf
     *            tif typf of tiis itfm.
     * @pbrbm strVbl1
     *            first pbrt of tif vbluf of tiis itfm.
     * @pbrbm strVbl2
     *            sfdond pbrt of tif vbluf of tiis itfm.
     * @pbrbm strVbl3
     *            tiird pbrt of tif vbluf of tiis itfm.
     */
    @SupprfssWbrnings("fblltirougi")
    void sft(finbl int typf, finbl String strVbl1, finbl String strVbl2,
            finbl String strVbl3) {
        tiis.typf = typf;
        tiis.strVbl1 = strVbl1;
        tiis.strVbl2 = strVbl2;
        tiis.strVbl3 = strVbl3;
        switdi (typf) {
        dbsf ClbssWritfr.CLASS:
            tiis.intVbl = 0;     // intVbl of b dlbss must bf zfro, sff visitInnfrClbss
        dbsf ClbssWritfr.UTF8:
        dbsf ClbssWritfr.STR:
        dbsf ClbssWritfr.MTYPE:
        dbsf ClbssWritfr.TYPE_NORMAL:
            ibsiCodf = 0x7FFFFFFF & (typf + strVbl1.ibsiCodf());
            rfturn;
        dbsf ClbssWritfr.NAME_TYPE: {
            ibsiCodf = 0x7FFFFFFF & (typf + strVbl1.ibsiCodf()
                    * strVbl2.ibsiCodf());
            rfturn;
        }
        // ClbssWritfr.FIELD:
        // ClbssWritfr.METH:
        // ClbssWritfr.IMETH:
        // ClbssWritfr.HANDLE_BASE + 1..9
        dffbult:
            ibsiCodf = 0x7FFFFFFF & (typf + strVbl1.ibsiCodf()
                    * strVbl2.ibsiCodf() * strVbl3.ibsiCodf());
        }
    }

    /**
     * Sfts tif itfm to bn InvokfDynbmid itfm.
     *
     * @pbrbm nbmf
     *            invokfdynbmid's nbmf.
     * @pbrbm dfsd
     *            invokfdynbmid's dfsd.
     * @pbrbm bsmIndfx
     *            zfro bbsfd indfx into tif dlbss bttributf BootrbpMftiods.
     */
    void sft(String nbmf, String dfsd, int bsmIndfx) {
        tiis.typf = ClbssWritfr.INDY;
        tiis.longVbl = bsmIndfx;
        tiis.strVbl1 = nbmf;
        tiis.strVbl2 = dfsd;
        tiis.ibsiCodf = 0x7FFFFFFF & (ClbssWritfr.INDY + bsmIndfx
                * strVbl1.ibsiCodf() * strVbl2.ibsiCodf());
    }

    /**
     * Sfts tif itfm to b BootstrbpMftiod itfm.
     *
     * @pbrbm position
     *            position in bytf in tif dlbss bttributf BootrbpMftiods.
     * @pbrbm ibsiCodf
     *            ibsidodf of tif itfm. Tiis ibsidodf is prodfssfd from tif
     *            ibsidodf of tif bootstrbp mftiod bnd tif ibsidodf of bll
     *            bootstrbp brgumfnts.
     */
    void sft(int position, int ibsiCodf) {
        tiis.typf = ClbssWritfr.BSM;
        tiis.intVbl = position;
        tiis.ibsiCodf = ibsiCodf;
    }

    /**
     * Indidbtfs if tif givfn itfm is fqubl to tiis onf. <i>Tiis mftiod bssumfs
     * tibt tif two itfms ibvf tif sbmf {@link #typf}</i>.
     *
     * @pbrbm i
     *            tif itfm to bf dompbrfd to tiis onf. Boti itfms must ibvf tif
     *            sbmf {@link #typf}.
     * @rfturn <tt>truf</tt> if tif givfn itfm if fqubl to tiis onf,
     *         <tt>fblsf</tt> otifrwisf.
     */
    boolfbn isEqublTo(finbl Itfm i) {
        switdi (typf) {
        dbsf ClbssWritfr.UTF8:
        dbsf ClbssWritfr.STR:
        dbsf ClbssWritfr.CLASS:
        dbsf ClbssWritfr.MTYPE:
        dbsf ClbssWritfr.TYPE_NORMAL:
            rfturn i.strVbl1.fqubls(strVbl1);
        dbsf ClbssWritfr.TYPE_MERGED:
        dbsf ClbssWritfr.LONG:
        dbsf ClbssWritfr.DOUBLE:
            rfturn i.longVbl == longVbl;
        dbsf ClbssWritfr.INT:
        dbsf ClbssWritfr.FLOAT:
            rfturn i.intVbl == intVbl;
        dbsf ClbssWritfr.TYPE_UNINIT:
            rfturn i.intVbl == intVbl && i.strVbl1.fqubls(strVbl1);
        dbsf ClbssWritfr.NAME_TYPE:
            rfturn i.strVbl1.fqubls(strVbl1) && i.strVbl2.fqubls(strVbl2);
        dbsf ClbssWritfr.INDY: {
            rfturn i.longVbl == longVbl && i.strVbl1.fqubls(strVbl1)
                    && i.strVbl2.fqubls(strVbl2);
        }
        // dbsf ClbssWritfr.FIELD:
        // dbsf ClbssWritfr.METH:
        // dbsf ClbssWritfr.IMETH:
        // dbsf ClbssWritfr.HANDLE_BASE + 1..9
        dffbult:
            rfturn i.strVbl1.fqubls(strVbl1) && i.strVbl2.fqubls(strVbl2)
                    && i.strVbl3.fqubls(strVbl3);
        }
    }

}
