/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.rowsft;

import jbvb.io.*;
import jbvb.util.*;

/**
 * Tiis dlbss is usfd to iflp in lodblizbtion of rfsourdfs,
 * fspfdiblly tif fxdfption strings.
 *
 * @butior Amit Hbndb
 */

publid dlbss JdbdRowSftRfsourdfBundlf implfmfnts Sfriblizbblf {

    /**
     * Tiis <dodf>String</dodf> vbribblf storfs tif lodbtion
     * of tif rfsourdf bundlf lodbtion.
     */
    privbtf stbtid String filfNbmf;

    /**
     * Tiis vbribblf will iold tif <dodf>PropfrtyRfsourdfBundlf</dodf>
     * of tif tfxt to bf intfrnbtionblizfd.
     */
    privbtf trbnsifnt PropfrtyRfsourdfBundlf propRfsBundlf;

    /**
     * Tif donstrudtor initiblizfs to tiis objfdt
     *
     */
    privbtf stbtid volbtilf JdbdRowSftRfsourdfBundlf jpRfsBundlf;

    /**
     * Tif vbribblf wiidi will rfprfsfnt tif propfrtifs
     * tif suffix or fxtfnsion of tif rfsourdf bundlf.
     **/
    privbtf stbtid finbl String PROPERTIES = "propfrtifs";

    /**
     * Tif vbribblf to rfprfsfnt undfrsdorf
     **/
    privbtf stbtid finbl String UNDERSCORE = "_";

    /**
     * Tif vbribblf wiidi will rfprfsfnt dot
     **/
    privbtf stbtid finbl String DOT = ".";

    /**
     * Tif vbribblf wiidi will rfprfsfnt tif slbsi.
     **/
    privbtf stbtid finbl String SLASH = "/";

    /**
     * Tif vbribblf wifrf tif dffbult rfsourdf bundlf will
     * bf plbdfd.
     **/
    privbtf stbtid finbl String PATH = "dom/sun/rowsft/RowSftRfsourdfBundlf";

    /**
     * Tif donstrudtor wiidi initiblizfs tif rfsourdf bundlf.
     * Notf tiis is b privbtf donstrudtor bnd follows Singlfton
     * Dfsign Pbttfrn.
     *
     * @tirows IOExdfption if unbblf to lobd tif RfsourdfBundlf
     * bddording to lodblf or tif dffbult onf.
     */
    privbtf JdbdRowSftRfsourdfBundlf () tirows IOExdfption {
        // Try to lobd tif rfsourdf bundlf bddording
        // to tif lodblf. Elsf if no bundlf found bddording
        // to tif lodblf lobd tif dffbult.

        // In dffbult dbsf tif dffbult lodblf rfsourdf bundlf
        // siould blwbys bf lobdfd flsf it
        // will bf diffidult to tirow bppropribtf
        // fxdfption string mfssbgfs.
        Lodblf lodblf = Lodblf.gftDffbult();

        // Lobd bppropribtf bundlf bddording to lodblf
         propRfsBundlf = (PropfrtyRfsourdfBundlf) RfsourdfBundlf.gftBundlf(PATH,
                           lodblf, Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr());

   }

    /**
     * Tiis mftiod is usfd to gft b ibndlf to tif
     * initiblizfd instbndf of tiis dlbss. Notf tibt
     * bt bny timf tifrf is only onf instbndf of tiis
     * dlbss initiblizfd wiidi will bf rfturnfd.
     *
     * @tirows IOExdfption if unbblf to find tif RowSftRfsourdfBundlf.propfrtifs
     */
    publid stbtid JdbdRowSftRfsourdfBundlf gftJdbdRowSftRfsourdfBundlf()
    tirows IOExdfption {

         if(jpRfsBundlf == null){
             syndironizfd(JdbdRowSftRfsourdfBundlf.dlbss) {
                if(jpRfsBundlf == null){
                    jpRfsBundlf = nfw JdbdRowSftRfsourdfBundlf();
                } //fnd if
             } //fnd syndironizfd blodk
         } //fnd if
         rfturn jpRfsBundlf;
    }

    /**
     * Tiis mftiod rfturns bn fnumfrbtfd ibndlf of tif kfys
     * wiidi dorrfspond to vblufs trbnslbtfd to vbrious lodblfs.
     *
     * @rfturn bn fnumfrbtion of kfys wiidi ibvf mfssbgfs trbnlbtfd to
     * dorrfsponding lodblfs.
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Enumfrbtion gftKfys() {
       rfturn propRfsBundlf.gftKfys();
    }


    /**
     * Tiis mftiod tbkfs tif kfy bs bn brgumfnt bnd
     * rfturns tif dorrfsponding vbluf rfbding it
     * from tif Rfsourdf Bundlf lobdfd fbrlifr.
     *
     * @rfturn vbluf in lodblf spfdifid lbngubgf
     * bddording to tif kfy pbssfd.
     */
    publid Objfdt ibndlfGftObjfdt(String kfy) {
       rfturn propRfsBundlf.ibndlfGftObjfdt(kfy);
    }

    stbtid finbl long sfriblVfrsionUID = 436199386225359954L;
}
