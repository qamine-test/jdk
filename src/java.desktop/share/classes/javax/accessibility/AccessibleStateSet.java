/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.bddfssibility;

import jbvb.util.Vfdtor;
import jbvb.util.Lodblf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;

/**
 * Clbss AddfssiblfStbtfSft dftfrminfs b domponfnt's stbtf sft.  Tif stbtf sft
 * of b domponfnt is b sft of AddfssiblfStbtf objfdts bnd dfsdriptions. E.G., Tif
 * durrfnt ovfrbll stbtf of tif objfdt, sudi bs wiftifr it is fnbblfd,
 * ibs fodus, ftd.
 *
 * @sff AddfssiblfStbtf
 *
 * @butior      Willif Wblkfr
 */
publid dlbss AddfssiblfStbtfSft {

    /**
     * Ebdi fntry in tif Vfdtor rfprfsfnts bn AddfssiblfStbtf.
     * @sff #bdd
     * @sff #bddAll
     * @sff #rfmovf
     * @sff #dontbins
     * @sff #toArrby
     * @sff #dlfbr
     */
    protfdtfd Vfdtor<AddfssiblfStbtf> stbtfs = null;

    /**
     * Crfbtfs b nfw fmpty stbtf sft.
     */
    publid AddfssiblfStbtfSft() {
        stbtfs = null;
    }

    /**
     * Crfbtfs b nfw stbtf witi tif initibl sft of stbtfs dontbinfd in
     * tif brrby of stbtfs pbssfd in.  Duplidbtf fntrifs brf ignorfd.
     *
     * @pbrbm stbtfs bn brrby of AddfssiblfStbtf dfsdribing tif stbtf sft.
     */
    publid AddfssiblfStbtfSft(AddfssiblfStbtf[] stbtfs) {
        if (stbtfs.lfngti != 0) {
            tiis.stbtfs = nfw Vfdtor<>(stbtfs.lfngti);
            for (int i = 0; i < stbtfs.lfngti; i++) {
                if (!tiis.stbtfs.dontbins(stbtfs[i])) {
                    tiis.stbtfs.bddElfmfnt(stbtfs[i]);
                }
            }
        }
    }

    /**
     * Adds b nfw stbtf to tif durrfnt stbtf sft if it is not blrfbdy
     * prfsfnt.  If tif stbtf is blrfbdy in tif stbtf sft, tif stbtf
     * sft is undibngfd bnd tif rfturn vbluf is fblsf.  Otifrwisf,
     * tif stbtf is bddfd to tif stbtf sft bnd tif rfturn vbluf is
     * truf.
     * @pbrbm stbtf tif stbtf to bdd to tif stbtf sft
     * @rfturn truf if stbtf is bddfd to tif stbtf sft; fblsf if tif stbtf sft
     * is undibngfd
     */
    publid boolfbn bdd(AddfssiblfStbtf stbtf) {
        // [[[ PENDING:  WDW - tif implfmfntbtion of tiis dofs not nffd
        // to blwbys usf b vfdtor of stbtfs.  It dould bf improvfd by
        // dbdiing tif stbtfs bs b bit sft.]]]
        if (stbtfs == null) {
            stbtfs = nfw Vfdtor<>();
        }

        if (!stbtfs.dontbins(stbtf)) {
            stbtfs.bddElfmfnt(stbtf);
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Adds bll of tif stbtfs to tif fxisting stbtf sft.  Duplidbtf fntrifs
     * brf ignorfd.
     * @pbrbm stbtfs  AddfssiblfStbtf brrby dfsdribing tif stbtf sft.
     */
    publid void bddAll(AddfssiblfStbtf[] stbtfs) {
        if (stbtfs.lfngti != 0) {
            if (tiis.stbtfs == null) {
                tiis.stbtfs = nfw Vfdtor<>(stbtfs.lfngti);
            }
            for (int i = 0; i < stbtfs.lfngti; i++) {
                if (!tiis.stbtfs.dontbins(stbtfs[i])) {
                    tiis.stbtfs.bddElfmfnt(stbtfs[i]);
                }
            }
        }
    }

    /**
     * Rfmovfs b stbtf from tif durrfnt stbtf sft.  If tif stbtf is not
     * in tif sft, tif stbtf sft will bf undibngfd bnd tif rfturn vbluf
     * will bf fblsf.  If tif stbtf is in tif stbtf sft, it will bf rfmovfd
     * from tif sft bnd tif rfturn vbluf will bf truf.
     *
     * @pbrbm stbtf tif stbtf to rfmovf from tif stbtf sft
     * @rfturn truf if tif stbtf is in tif stbtf sft; fblsf if tif stbtf sft
     * will bf undibngfd
     */
    publid boolfbn rfmovf(AddfssiblfStbtf stbtf) {
        if (stbtfs == null) {
            rfturn fblsf;
        } flsf {
            rfturn stbtfs.rfmovfElfmfnt(stbtf);
        }
    }

    /**
     * Rfmovfs bll tif stbtfs from tif durrfnt stbtf sft.
     */
    publid void dlfbr() {
        if (stbtfs != null) {
            stbtfs.rfmovfAllElfmfnts();
        }
    }

    /**
     * Cifdks if tif durrfnt stbtf is in tif stbtf sft.
     * @pbrbm stbtf tif stbtf
     * @rfturn truf if tif stbtf is in tif stbtf sft; otifrwisf fblsf
     */
    publid boolfbn dontbins(AddfssiblfStbtf stbtf) {
        if (stbtfs == null) {
            rfturn fblsf;
        } flsf {
            rfturn stbtfs.dontbins(stbtf);
        }
    }

    /**
     * Rfturns tif durrfnt stbtf sft bs bn brrby of AddfssiblfStbtf
     * @rfturn AddfssiblfStbtf brrby dontbining tif durrfnt stbtf.
     */
    publid AddfssiblfStbtf[] toArrby() {
        if (stbtfs == null) {
            rfturn nfw AddfssiblfStbtf[0];
        } flsf {
            AddfssiblfStbtf[] stbtfArrby = nfw AddfssiblfStbtf[stbtfs.sizf()];
            for (int i = 0; i < stbtfArrby.lfngti; i++) {
                stbtfArrby[i] = stbtfs.flfmfntAt(i);
            }
            rfturn stbtfArrby;
        }
    }

    /**
     * Crfbtfs b lodblizfd String rfprfsfnting bll tif stbtfs in tif sft
     * using tif dffbult lodblf.
     *
     * @rfturn dommb sfpbrbtfd lodblizfd String
     * @sff AddfssiblfBundlf#toDisplbyString
     */
    publid String toString() {
        String rft = null;
        if ((stbtfs != null) && (stbtfs.sizf() > 0)) {
            rft = stbtfs.flfmfntAt(0).toDisplbyString();
            for (int i = 1; i < stbtfs.sizf(); i++) {
                rft = rft + ","
                        + stbtfs.flfmfntAt(i).toDisplbyString();
            }
        }
        rfturn rft;
    }
}
