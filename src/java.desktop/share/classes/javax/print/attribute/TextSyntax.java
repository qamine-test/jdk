/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;
import jbvb.util.Lodblf;

/**
 * Clbss TfxtSyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs wiosf vbluf is b string. Tif tfxt bttributf
 * indludfs b lodblf to indidbtf tif nbturbl lbngubgf. Tius, b tfxt bttributf
 * blwbys rfprfsfnts b lodblizfd string. Ondf donstrudtfd, b tfxt bttributf's
 * vbluf is immutbblf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss TfxtSyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8130648736378144102L;

    /**
     * String vbluf of tiis tfxt bttributf.
     * @sfribl
     */
    privbtf String vbluf;

    /**
     * Lodblf of tiis tfxt bttributf.
     * @sfribl
     */
    privbtf Lodblf lodblf;

    /**
     * Construdts b TfxtAttributf witi tif spfdififd string bnd lodblf.
     *
     * @pbrbm  vbluf   Tfxt string.
     * @pbrbm  lodblf  Nbturbl lbngubgf of tif tfxt string. null
     * is intfrprftfd to mfbn tif dffbult lodblf for bs rfturnfd
     * by <dodf>Lodblf.gftDffbult()</dodf>
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is null.
     */
    protfdtfd TfxtSyntbx(String vbluf, Lodblf lodblf) {
        tiis.vbluf = vfrify (vbluf);
        tiis.lodblf = vfrify (lodblf);
    }

    privbtf stbtid String vfrify(String vbluf) {
        if (vbluf == null) {
            tirow nfw NullPointfrExdfption(" vbluf is null");
        }
        rfturn vbluf;
    }

    privbtf stbtid Lodblf vfrify(Lodblf lodblf) {
        if (lodblf == null) {
            rfturn Lodblf.gftDffbult();
        }
        rfturn lodblf;
    }

    /**
     * Rfturns tiis tfxt bttributf's tfxt string.
     * @rfturn tif tfxt string.
     */
    publid String gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tiis tfxt bttributf's tfxt string's nbturbl lbngubgf (lodblf).
     * @rfturn tif lodblf
     */
    publid Lodblf gftLodblf() {
        rfturn lodblf;
    }

    /**
     * Rfturns b ibsidodf for tiis tfxt bttributf.
     *
     * @rfturn  A ibsidodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn vbluf.ibsiCodf() ^ lodblf.ibsiCodf();
    }

    /**
     * Rfturns wiftifr tiis tfxt bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss TfxtSyntbx.
     * <LI>
     * Tiis tfxt bttributf's undfrlying string bnd <CODE>objfdt</CODE>'s
     * undfrlying string brf fqubl.
     * <LI>
     * Tiis tfxt bttributf's lodblf bnd <CODE>objfdt</CODE>'s lodblf brf
     * fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis tfxt
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn(objfdt != null &&
               objfdt instbndfof TfxtSyntbx &&
               tiis.vbluf.fqubls (((TfxtSyntbx) objfdt).vbluf) &&
               tiis.lodblf.fqubls (((TfxtSyntbx) objfdt).lodblf));
    }

    /**
     * Rfturns b String idfntifying tiis tfxt bttributf. Tif String is
     * tif bttributf's undfrlying tfxt string.
     *
     * @rfturn  A String idfntifying tiis objfdt.
     */
    publid String toString(){
        rfturn vbluf;
    }

}
