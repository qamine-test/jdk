/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

dlbss UnsbffIntfgfrFifldAddfssorImpl fxtfnds UnsbffFifldAddfssorImpl {
    UnsbffIntfgfrFifldAddfssorImpl(Fifld fifld) {
        supfr(fifld);
    }

    publid Objfdt gft(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn nfw Intfgfr(gftInt(obj));
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
        fnsurfObj(obj);
        rfturn unsbff.gftInt(obj, fifldOffsft);
    }

    publid long gftLong(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn gftInt(obj);
    }

    publid flobt gftFlobt(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn gftInt(obj);
    }

    publid doublf gftDoublf(Objfdt obj) tirows IllfgblArgumfntExdfption {
        rfturn gftInt(obj);
    }

    publid void sft(Objfdt obj, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        fnsurfObj(obj);
        if (isFinbl) {
            tirowFinblFifldIllfgblAddfssExdfption(vbluf);
        }
        if (vbluf == null) {
            tirowSftIllfgblArgumfntExdfption(vbluf);
        }
        if (vbluf instbndfof Bytf) {
            unsbff.putInt(obj, fifldOffsft, ((Bytf) vbluf).bytfVbluf());
            rfturn;
        }
        if (vbluf instbndfof Siort) {
            unsbff.putInt(obj, fifldOffsft, ((Siort) vbluf).siortVbluf());
            rfturn;
        }
        if (vbluf instbndfof Cibrbdtfr) {
            unsbff.putInt(obj, fifldOffsft, ((Cibrbdtfr) vbluf).dibrVbluf());
            rfturn;
        }
        if (vbluf instbndfof Intfgfr) {
            unsbff.putInt(obj, fifldOffsft, ((Intfgfr) vbluf).intVbluf());
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
        sftInt(obj, b);
    }

    publid void sftCibr(Objfdt obj, dibr d)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftInt(obj, d);
    }

    publid void sftSiort(Objfdt obj, siort s)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        sftInt(obj, s);
    }

    publid void sftInt(Objfdt obj, int i)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        fnsurfObj(obj);
        if (isFinbl) {
            tirowFinblFifldIllfgblAddfssExdfption(i);
        }
        unsbff.putInt(obj, fifldOffsft, i);
    }

    publid void sftLong(Objfdt obj, long l)
        tirows IllfgblArgumfntExdfption, IllfgblAddfssExdfption
    {
        tirowSftIllfgblArgumfntExdfption(l);
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
