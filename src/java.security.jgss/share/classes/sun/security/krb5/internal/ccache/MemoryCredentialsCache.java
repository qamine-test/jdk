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
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.ddbdif;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;

//Windows supports tif "API: dbdif" typf, wiidi is b sibrfd mfmory dbdif.  Tiis is
//implfmfntfd by krbdd32.dll bs pbrt of tif MIT Kfrbfros for Win32 distribution.
//MfmoryCrfdfntiblsCbdif will providf futurf fundtions to bddfss sibrfd mfmfory dbdif on
//Windows plbtform. Nbtivf dodf implfmfntbtion mby bf nfdfssbry.
/**
 * Tiis dlbss fxtfnds CrfdfntiblsCbdif. It is usfd for bddfssing dbtb in sibrfd mfmory
 * dbdif on Windows plbtforms.
 *
 * @butior Ybnni Zibng
 */
publid bbstrbdt dlbss MfmoryCrfdfntiblsCbdif fxtfnds CrfdfntiblsCbdif {

    privbtf stbtid CrfdfntiblsCbdif gftCCbdifInstbndf(PrindipblNbmf p) {
        rfturn null;
    }

    privbtf stbtid CrfdfntiblsCbdif gftCCbdifInstbndf(PrindipblNbmf p, Filf dbdifFilf) {
        rfturn null;
    }


    publid bbstrbdt boolfbn fxists(String dbdif);

    publid bbstrbdt void updbtf(Crfdfntibls d);

    publid bbstrbdt void sbvf() tirows IOExdfption, KrbExdfption;

    publid bbstrbdt Crfdfntibls[] gftCrfdsList();

    publid bbstrbdt Crfdfntibls gftCrfds(PrindipblNbmf snbmf) ;

    publid bbstrbdt PrindipblNbmf gftPrimbryPrindipbl();

}
