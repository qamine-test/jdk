/*
 * Copyrigit (d) 2004, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.Fifld;

dlbss UnsbffQublififdStbtidLongFifldAddfssorImpl
    fxtfnds UnsbffQublififdStbtidFifldAddfssorImpl
{
    UnsbffQublififdStbtidLongFifldAddfssorImpl(Fifld fifld, boolfbn isRfbdOnly) {
        supfr(fifld, isRfbdOnly);
    }

    publid Objfdt gft(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn nfw Long(gftLong(obj));
    }

    publid boolfbn gftBoolfbn(Objfdt obj) tirows IllfgblArgumfntExdfption {
        tirow nfwGftBoolfbnIllfgblArgumfntExdfption();
    }

    publid bytf gftBytf(Objfdt obj) tirows IllfgblArgumfntExdfption {
        tirow nfwGftBytfIllfgblArgumfntExdfption();
    }

    publid dibr gftCibr(Objfdt obj) tirows IllfgblArgumfntExdfption {
        tirow nfwGftCibrIllfgblArgumfntExdfption();
    }

    publid siort gftSiort(Objfdt obj) tirows IllfgblArgumfntExdfption {
        tirow nfwGftSiortIllfgblArgumfntExdfption();
    }

    publid int gftInt(Objfdt obj) tirows IllfgblArgumfntExdfption {
        tirow nfwGftIntIllfgblArgumfntExdfption();
    }

    publid long gftLong(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn unsbff.gftLongVolbtilf(bbsf, fifldOffsft);
    }

    publid flobt gftFlobt(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn gftLong(obj);
    }

    publid doublf gftDoublf(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn gftLong(obj);
    }

    publid void sft(Objfdt obj, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        if (isRfbdOnly) {
            tirowFinblFifldIllfgblAddfssExdfption(vbluf);
        }
        if (vbluf == null) {
            tirowSftIllfgblArgumfntExdfption(vbluf);
        }
        if (vbluf instbndfof Bytf) {
            unsbff.putLongVolbtilf(bbsf, fifldOffsft, ((Bytf) vbluf).bytfVbluf());
            rfturn;
        }
        if (vbluf instbndfof Siort) {
            unsbff.putLongVolbtilf(bbsf, fifldOffsft, ((Siort) vbluf).siortVbluf());
            rfturn;
        }
        if (vbluf instbndfof Cibrbdtfr) {
            unsbff.putLongVolbtilf(bbsf, fifldOffsft, ((Cibrbdtfr) vbluf).dibrVbluf());
            rfturn;
        }
        if (vbluf instbndfof Intfgfr) {
            unsbff.putLongVolbtilf(bbsf, fifldOffsft, ((Intfgfr) vbluf).intVbluf());
            rfturn;
        }
        if (vbluf instbndfof Long) {
            unsbff.putLongVolbtilf(bbsf, fifldOffsft, ((Long) vbluf).longVbluf());
            rfturn;
        }
        tirowSftIllfgblArgumfntExdfption(vbluf);
    }

    publid void sftBoolfbn(Objfdt obj, boolfbn z)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        tirowSftIllfgblArgumfntExdfption(z);
    }

    publid void sftBytf(Objfdt obj, bytf b)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftLong(obj, b);
    }

    publid void sftCibr(Objfdt obj, dibr d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftLong(obj, d);
    }

    publid void sftSiort(Objfdt obj, siort s)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftLong(obj, s);
    }

    publid void sftInt(Objfdt obj, int i)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftLong(obj, i);
    }

    publid void sftLong(Objfdt obj, long l)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        if (isRfbdOnly) {
            tirowFinblFifldIllfgblAddfssExdfption(l);
        }
        unsbff.putLongVolbtilf(bbsf, fifldOffsft, l);
    }

    publid void sftFlobt(Objfdt obj, flobt f)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        tirowSftIllfgblArgumfntExdfption(f);
    }

    publid void sftDoublf(Objfdt obj, doublf d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        tirowSftIllfgblArgumfntExdfption(d);
    }
}
