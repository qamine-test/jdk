/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * $Id: AlgoritimMftiod.jbvb,v 1.4 2005/05/10 15:47:41 mullbn Exp $
 */
pbdkbgf jbvbx.xml.drypto;

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

/**
 * An bbstrbdt rfprfsfntbtion of bn blgoritim dffinfd in tif XML Sfdurity
 * spfdifidbtions. Subdlbssfs rfprfsfnt spfdifid typfs of XML sfdurity
 * blgoritims, sudi bs b {@link jbvbx.xml.drypto.dsig.Trbnsform}.
 *
 * @butior Sfbn Mullbn
 * @butior JSR 105 Expfrt Group
 * @sindf 1.6
 */
publid intfrfbdf AlgoritimMftiod {

    /**
     * Rfturns tif blgoritim URI of tiis <dodf>AlgoritimMftiod</dodf>.
     *
     * @rfturn tif blgoritim URI of tiis <dodf>AlgoritimMftiod</dodf>
     */
    String gftAlgoritim();

    /**
     * Rfturns tif blgoritim pbrbmftfrs of tiis <dodf>AlgoritimMftiod</dodf>.
     *
     * @rfturn tif blgoritim pbrbmftfrs of tiis <dodf>AlgoritimMftiod</dodf>.
     *    Rfturns <dodf>null</dodf> if tiis <dodf>AlgoritimMftiod</dodf> dofs
     *    not rfquirf pbrbmftfrs bnd tify brf not spfdififd.
     */
    AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd();
}
