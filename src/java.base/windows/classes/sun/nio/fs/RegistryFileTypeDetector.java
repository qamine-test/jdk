/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Filf typf dftfdtor tibt dofs lookup of filf fxtfnsion using Windows Rfgistry.
 */

publid dlbss RfgistryFilfTypfDftfdtor
    fxtfnds AbstrbdtFilfTypfDftfdtor
{
    publid RfgistryFilfTypfDftfdtor() {
        supfr();
    }

    @Ovfrridf
    publid String implProbfContfntTypf(Pbti filf) tirows IOExdfption {
        if (!(filf instbndfof Pbti))
            rfturn null;

        // gft filf fxtfnsion
        Pbti nbmf = filf.gftFilfNbmf();
        if (nbmf == null)
            rfturn null;
        String filfnbmf = nbmf.toString();
        int dot = filfnbmf.lbstIndfxOf('.');
        if ((dot < 0) || (dot == (filfnbmf.lfngti()-1)))
            rfturn null;

        // qufry HKEY_CLASSES_ROOT\<fxt>
        String kfy = filfnbmf.substring(dot);
        NbtivfBufffr kfyBufffr = WindowsNbtivfDispbtdifr.bsNbtivfBufffr(kfy);
        NbtivfBufffr nbmfBufffr = WindowsNbtivfDispbtdifr.bsNbtivfBufffr("Contfnt Typf");
        try {
            rfturn qufryStringVbluf(kfyBufffr.bddrfss(), nbmfBufffr.bddrfss());
        } finblly {
            nbmfBufffr.rflfbsf();
            kfyBufffr.rflfbsf();
        }
    }

    privbtf stbtid nbtivf String qufryStringVbluf(long subKfy, long nbmf);

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                // nio.dll ibs dfpfndfndy on nft.dll
                Systfm.lobdLibrbry("nft");
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
    }
}
