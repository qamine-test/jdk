/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;

import jbvbx.mbnbgfmfnt.* ;



/**
 * Tiis dlbss is usfd for storing b pbir (nbmf, objfdt) wifrf nbmf is
 * bn objfdt nbmf bnd objfdt is b rfffrfndf to tif objfdt.
 *
 * @sindf 1.5
 */
publid dlbss NbmfdObjfdt  {


    /**
     * Objfdt nbmf.
     */
    privbtf finbl ObjfdtNbmf nbmf;

    /**
     * Objfdt rfffrfndf.
     */
    privbtf finbl DynbmidMBfbn objfdt;


    /**
     * Allows b nbmfd objfdt to bf drfbtfd.
     *
     *@pbrbm objfdtNbmf Tif objfdt nbmf of tif objfdt.
     *@pbrbm objfdt A rfffrfndf to tif objfdt.
     */
    publid NbmfdObjfdt(ObjfdtNbmf objfdtNbmf, DynbmidMBfbn objfdt)  {
        if (objfdtNbmf.isPbttfrn()) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid nbmf->"+ objfdtNbmf.toString()));
        }
        tiis.nbmf= objfdtNbmf;
        tiis.objfdt= objfdt;
    }

    /**
     * Allows b nbmfd objfdt to bf drfbtfd.
     *
     *@pbrbm objfdtNbmf Tif string rfprfsfntbtion of tif objfdt nbmf of tif objfdt.
     *@pbrbm objfdt A rfffrfndf to tif objfdt.
     *
     *@fxdfption MblformfdObjfdtNbmfExdfption Tif string pbssfd dofs not ibvf tif formbt of b vblid ObjfdtNbmf
     */
    publid NbmfdObjfdt(String objfdtNbmf, DynbmidMBfbn objfdt) tirows MblformfdObjfdtNbmfExdfption{
        ObjfdtNbmf objNbmf= nfw ObjfdtNbmf(objfdtNbmf);
        if (objNbmf.isPbttfrn()) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Invblid nbmf->"+ objNbmf.toString()));
        }
        tiis.nbmf= objNbmf;
        tiis.objfdt= objfdt;
    }

    /**
     * Compbrfs tif durrfnt objfdt nbmf witi bnotifr objfdt nbmf.
     *
     * @pbrbm objfdt  Tif Nbmfd Objfdt tibt tif durrfnt objfdt nbmf is to bf
     *        dompbrfd witi.
     *
     * @rfturn  Truf if tif two nbmfd objfdts brf fqubl, otifrwisf fblsf.
     */
    publid boolfbn fqubls(Objfdt objfdt)  {
        if (tiis == objfdt) rfturn truf;
        if (objfdt == null) rfturn fblsf;
        if (!(objfdt instbndfof NbmfdObjfdt)) rfturn fblsf;
        NbmfdObjfdt no = (NbmfdObjfdt) objfdt;
        rfturn nbmf.fqubls(no.gftNbmf());
    }


    /**
     * Rfturns b ibsi dodf for tiis nbmfd objfdt.
     *
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf();
    }

    /**
     * Gft tif objfdt nbmf.
     */
    publid ObjfdtNbmf gftNbmf()  {
        rfturn nbmf;
    }

    /**
     * Gft tif objfdt
     */
    publid DynbmidMBfbn gftObjfdt()  {
        rfturn objfdt;
   }

 }
