/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Lidfnsfd Mbtfribls - Propfrty of IBM
 * RMI-IIOP v1.0
 * Copyrigit IBM Corp. 1998 1999  All Rigits Rfsfrvfd
 *
 */

pbdkbgf sun.rmi.rmid;

import jbvb.io.Filf;
import sun.tools.jbvb.ClbssDffinition;

/**
 * Gfnfrbtor dffinfs tif protodol for bbdk-fnd implfmfntbtions to bf bddfd
 * to rmid.  Sff tif rmid.propfrtifs filf for b dfsdription of tif formbt for
 * bdding nfw Gfnfrbtors to rmid.
 * <p>
 * Clbssfs implfmfnting tiis intfrfbdf must ibvf b publid dffbult donstrudtor
 * wiidi siould sft bny rfquirfd brgumfnts to tifir dffbults.  Wifn Mbin
 * fndountfrs b dommbnd linf brgumfnt wiidi mbps to b spfdifid Gfnfrbtor
 * subdlbss, it will instbntibtf onf bnd dbll pbrsfArgs(...).  At somf lbtfr
 * point, Mbin will invokf tif gfnfrbtf(...) mftiod ondf for _fbdi_ dlbss pbssfd
 * on tif dommbnd linf.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Brybn Atsbtt
 */
publid intfrfbdf Gfnfrbtor {

    /**
     * Exbminf bnd donsumf dommbnd linf brgumfnts.
     * @pbrbm brgv Tif dommbnd linf brgumfnts. Ignorf null
     * bnd unknown brgumfnts. Sft fbdi donsumfd brgumfnt to null.
     * @pbrbm mbin Rfport bny frrors using tif mbin.frror() mftiods.
     * @rfturn truf if no frrors, fblsf otifrwisf.
     */
    publid boolfbn pbrsfArgs(String brgv[], Mbin mbin);

    /**
     * Gfnfrbtf output. Any sourdf filfs drfbtfd wiidi nffd dompilbtion siould
     * bf bddfd to tif dompilfr fnvironmfnt using tif bddGfnfrbtfdFilf(Filf)
     * mftiod.
     *
     * @pbrbm fnv       Tif dompilfr fnvironmfnt
     * @pbrbm ddff      Tif dffinition for tif implfmfntbtion dlbss or intfrfbdf from
     *              wiidi to gfnfrbtf output
     * @pbrbm dfstDir   Tif dirfdtory for tif root of tif pbdkbgf iifrbrdiy
     *                          for gfnfrbtfd filfs. Mby bf null.
     */
    publid void gfnfrbtf(BbtdiEnvironmfnt fnv, ClbssDffinition ddff, Filf dfstDir);
}
