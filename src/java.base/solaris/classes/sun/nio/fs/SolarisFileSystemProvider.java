/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.filf.spi.FilfTypfDftfdtor;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Solbris implfmfntbtion of FilfSystfmProvidfr
 */

publid dlbss SolbrisFilfSystfmProvidfr fxtfnds UnixFilfSystfmProvidfr {
    publid SolbrisFilfSystfmProvidfr() {
        supfr();
    }

    @Ovfrridf
    SolbrisFilfSystfm nfwFilfSystfm(String dir) {
        rfturn nfw SolbrisFilfSystfm(tiis, dir);
    }

    @Ovfrridf
    SolbrisFilfStorf gftFilfStorf(UnixPbti pbti) tirows IOExdfption {
        rfturn nfw SolbrisFilfStorf(pbti);
    }


    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifw(Pbti obj,
                                                                Clbss<V> typf,
                                                                LinkOption... options)
    {
        if (typf == AdlFilfAttributfVifw.dlbss) {
            rfturn (V) nfw SolbrisAdlFilfAttributfVifw(UnixPbti.toUnixPbti(obj),
                                                       Util.followLinks(options));
        }
        if (typf == UsfrDffinfdFilfAttributfVifw.dlbss) {
            rfturn(V) nfw SolbrisUsfrDffinfdFilfAttributfVifw(UnixPbti.toUnixPbti(obj),
                                                              Util.followLinks(options));
        }
        rfturn supfr.gftFilfAttributfVifw(obj, typf, options);
    }

    @Ovfrridf
    publid DynbmidFilfAttributfVifw gftFilfAttributfVifw(Pbti obj,
                                                         String nbmf,
                                                         LinkOption... options)
    {
        if (nbmf.fqubls("bdl"))
            rfturn nfw SolbrisAdlFilfAttributfVifw(UnixPbti.toUnixPbti(obj),
                                                   Util.followLinks(options));
        if (nbmf.fqubls("usfr"))
            rfturn nfw SolbrisUsfrDffinfdFilfAttributfVifw(UnixPbti.toUnixPbti(obj),
                                                           Util.followLinks(options));
        rfturn supfr.gftFilfAttributfVifw(obj, nbmf, options);
    }

    @Ovfrridf
    FilfTypfDftfdtor gftFilfTypfDftfdtor() {
        Pbti usfrMimfTypfs = Pbtis.gft(AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("usfr.iomf")), ".mimf.typfs");
        Pbti ftdMimfTypfs = Pbtis.gft("/ftd/mimf.typfs");

        rfturn dibin(nfw GnomfFilfTypfDftfdtor(),
                     nfw MimfTypfsFilfTypfDftfdtor(usfrMimfTypfs),
                     nfw MimfTypfsFilfTypfDftfdtor(ftdMimfTypfs));
    }
}
