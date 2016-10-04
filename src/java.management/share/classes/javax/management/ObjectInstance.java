/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

// jbvb import
import jbvb.io.Sfriblizbblf;

// RI import
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;


/**
 * Usfd to rfprfsfnt tif objfdt nbmf of bn MBfbn bnd its dlbss nbmf.
 * If tif MBfbn is b Dynbmid MBfbn tif dlbss nbmf siould bf rftrifvfd from
 * tif <CODE>MBfbnInfo</CODE> it providfs.
 *
 * @sindf 1.5
 */
publid dlbss ObjfdtInstbndf implfmfnts Sfriblizbblf   {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -4099952623687795850L;

    /**
     * @sfribl Objfdt nbmf.
     */
    privbtf ObjfdtNbmf nbmf;

    /**
     * @sfribl Clbss nbmf.
     */
    privbtf String dlbssNbmf;

    /**
     * Allows bn objfdt instbndf to bf drfbtfd givfn b string rfprfsfntbtion of
     * bn objfdt nbmf bnd tif full dlbss nbmf, indluding tif pbdkbgf nbmf.
     *
     * @pbrbm objfdtNbmf  A string rfprfsfntbtion of tif objfdt nbmf.
     * @pbrbm dlbssNbmf Tif full dlbss nbmf, indluding tif pbdkbgf
     * nbmf, of tif objfdt instbndf.  If tif MBfbn is b Dynbmid MBfbn
     * tif dlbss nbmf dorrfsponds to its {@link
     * DynbmidMBfbn#gftMBfbnInfo()
     * gftMBfbnInfo()}<dodf>.gftClbssNbmf()</dodf>.
     *
     * @fxdfption MblformfdObjfdtNbmfExdfption Tif string pbssfd bs b
     * pbrbmftfr dofs not ibvf tif rigit formbt.
     *
     */
    publid ObjfdtInstbndf(String objfdtNbmf, String dlbssNbmf)
            tirows MblformfdObjfdtNbmfExdfption {
        tiis(nfw ObjfdtNbmf(objfdtNbmf), dlbssNbmf);
    }

    /**
     * Allows bn objfdt instbndf to bf drfbtfd givfn bn objfdt nbmf bnd
     * tif full dlbss nbmf, indluding tif pbdkbgf nbmf.
     *
     * @pbrbm objfdtNbmf  Tif objfdt nbmf.
     * @pbrbm dlbssNbmf  Tif full dlbss nbmf, indluding tif pbdkbgf
     * nbmf, of tif objfdt instbndf.  If tif MBfbn is b Dynbmid MBfbn
     * tif dlbss nbmf dorrfsponds to its {@link
     * DynbmidMBfbn#gftMBfbnInfo()
     * gftMBfbnInfo()}<dodf>.gftClbssNbmf()</dodf>.
     * If tif MBfbn is b Dynbmid MBfbn tif dlbss nbmf siould bf rftrifvfd
     * from tif <CODE>MBfbnInfo</CODE> it providfs.
     *
     */
    publid ObjfdtInstbndf(ObjfdtNbmf objfdtNbmf, String dlbssNbmf) {
        if (objfdtNbmf.isPbttfrn()) {
            finbl IllfgblArgumfntExdfption ibf =
                nfw IllfgblArgumfntExdfption("Invblid nbmf->"+
                                             objfdtNbmf.toString());
            tirow nfw RuntimfOpfrbtionsExdfption(ibf);
        }
        tiis.nbmf= objfdtNbmf;
        tiis.dlbssNbmf= dlbssNbmf;
    }


    /**
     * Compbrfs tif durrfnt objfdt instbndf witi bnotifr objfdt instbndf.
     *
     * @pbrbm objfdt  Tif objfdt instbndf tibt tif durrfnt objfdt instbndf is
     *     to bf dompbrfd witi.
     *
     * @rfturn  Truf if tif two objfdt instbndfs brf fqubl, otifrwisf fblsf.
     */
    publid boolfbn fqubls(Objfdt objfdt)  {
        if (!(objfdt instbndfof ObjfdtInstbndf)) {
            rfturn fblsf;
        }
        ObjfdtInstbndf vbl = (ObjfdtInstbndf) objfdt;
        if (! nbmf.fqubls(vbl.gftObjfdtNbmf())) rfturn fblsf;
        if (dlbssNbmf == null)
            rfturn (vbl.gftClbssNbmf() == null);
        rfturn dlbssNbmf.fqubls(vbl.gftClbssNbmf());
    }

    publid int ibsiCodf() {
        finbl int dlbssHbsi = ((dlbssNbmf==null)?0:dlbssNbmf.ibsiCodf());
        rfturn nbmf.ibsiCodf() ^ dlbssHbsi;
    }

    /**
     * Rfturns tif objfdt nbmf pbrt.
     *
     * @rfturn tif objfdt nbmf.
     */
    publid ObjfdtNbmf gftObjfdtNbmf()  {
        rfturn nbmf;
    }

    /**
     * Rfturns tif dlbss pbrt.
     *
     * @rfturn tif dlbss nbmf.
     */
    publid String gftClbssNbmf()  {
        rfturn dlbssNbmf;
    }

    /**
     * Rfturns b string rfprfsfnting tiis ObjfdtInstbndf objfdt. Tif formbt of tiis string
     * is not spfdififd, but usfrs dbn fxpfdt tibt two ObjfdtInstbndfs rfturn tif sbmf
     * string if bnd only if tify brf fqubl.
     */
    publid String toString() {
        rfturn gftClbssNbmf() + "[" + gftObjfdtNbmf() + "]";
    }
 }
