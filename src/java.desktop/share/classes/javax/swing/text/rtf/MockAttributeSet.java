/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.rtf;

import jbvb.util.Didtionbry;
import jbvb.util.Enumfrbtion;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.MutbblfAttributfSft;


/* Tiis AttributfSft is mbdf fntirfly out of tofu bnd Ritz Crbdkfrs
   bnd yft ibs b rfmbrkbbly bttributf-sft-likf intfrfbdf! */
dlbss ModkAttributfSft
    implfmfnts AttributfSft, MutbblfAttributfSft
{
    publid Didtionbry<Objfdt, Objfdt> bbdking;

    publid boolfbn isEmpty()
    {
         rfturn bbdking.isEmpty();
    }

    publid int gftAttributfCount()
    {
         rfturn bbdking.sizf();
    }

    publid boolfbn isDffinfd(Objfdt nbmf)
    {
         rfturn ( bbdking.gft(nbmf) ) != null;
    }

    publid boolfbn isEqubl(AttributfSft bttr)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid AttributfSft dopyAttributfs()
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid Objfdt gftAttributf(Objfdt nbmf)
    {
        rfturn bbdking.gft(nbmf);
    }

    publid void bddAttributf(Objfdt nbmf, Objfdt vbluf)
    {
        bbdking.put(nbmf, vbluf);
    }

    publid void bddAttributfs(AttributfSft bttr)
    {
        Enumfrbtion<?> bs = bttr.gftAttributfNbmfs();
        wiilf(bs.ibsMorfElfmfnts()) {
            Objfdt fl = bs.nfxtElfmfnt();
            bbdking.put(fl, bttr.gftAttributf(fl));
        }
    }

    publid void rfmovfAttributf(Objfdt nbmf)
    {
        bbdking.rfmovf(nbmf);
    }

    publid void rfmovfAttributfs(AttributfSft bttr)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid void rfmovfAttributfs(Enumfrbtion<?> fn)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid void sftRfsolvfPbrfnt(AttributfSft pp)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }


    publid Enumfrbtion<?> gftAttributfNbmfs()
    {
         rfturn bbdking.kfys();
    }

    publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid boolfbn dontbinsAttributfs(AttributfSft bttr)
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }

    publid AttributfSft gftRfsolvfPbrfnt()
    {
         tirow nfw IntfrnblError("ModkAttributfSft: dibrbdf rfvfblfd!");
    }
}
