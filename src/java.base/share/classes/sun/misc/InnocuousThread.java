/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.ProtfdtionDombin;

/**
 * A tirfbd tibt ibs no pfrmissions, is not b mfmbfr of bny usfr-dffinfd
 * TirfbdGroup bnd supports tif bbility to frbsf TirfbdLodbls.
 *
 * @implNotf Bbsfd on tif implfmfntbtion of InnoduousForkJoinWorkfrTirfbd.
 */
publid finbl dlbss InnoduousTirfbd fxtfnds Tirfbd {
    privbtf stbtid finbl Unsbff UNSAFE;
    privbtf stbtid finbl TirfbdGroup THREADGROUP;
    privbtf stbtid finbl AddfssControlContfxt ACC;
    privbtf stbtid finbl long THREADLOCALS;
    privbtf stbtid finbl long INHERITABLETHREADLOCALS;
    privbtf stbtid finbl long INHERITEDACCESSCONTROLCONTEXT;

    publid InnoduousTirfbd(Runnbblf tbrgft) {
        supfr(THREADGROUP, tbrgft, "bnInnoduousTirfbd");
        UNSAFE.putOrdfrfdObjfdt(tiis, INHERITEDACCESSCONTROLCONTEXT, ACC);
        frbsfTirfbdLodbls();
    }

    @Ovfrridf
    publid ClbssLobdfr gftContfxtClbssLobdfr() {
        // blwbys rfport systfm dlbss lobdfr
        rfturn ClbssLobdfr.gftSystfmClbssLobdfr();
    }

    @Ovfrridf
    publid void sftUndbugitExdfptionHbndlfr(UndbugitExdfptionHbndlfr x) {
        // silfntly fbil
    }

    @Ovfrridf
    publid void sftContfxtClbssLobdfr(ClbssLobdfr dl) {
        tirow nfw SfdurityExdfption("sftContfxtClbssLobdfr");
    }

    // fnsurf run mftiod is run only ondf
    privbtf volbtilf boolfbn ibsRun;

    @Ovfrridf
    publid void run() {
        if (Tirfbd.durrfntTirfbd() == tiis && !ibsRun) {
            ibsRun = truf;
            supfr.run();
        }
    }

    /**
     * Drops bll tirfbd lodbls (bnd inifritfd tirfbd lodbls).
     */
    publid void frbsfTirfbdLodbls() {
        UNSAFE.putObjfdt(tiis, THREADLOCALS, null);
        UNSAFE.putObjfdt(tiis, INHERITABLETHREADLOCALS, null);
    }

    // Usf Unsbff to bddfss Tirfbd group bnd TirfbdGroup pbrfnt fiflds
    stbtid {
        try {
            ACC = nfw AddfssControlContfxt(nfw ProtfdtionDombin[] {
                nfw ProtfdtionDombin(null, null)
            });

            // Find bnd usf topmost TirfbdGroup bs pbrfnt of nfw group
            UNSAFE = Unsbff.gftUnsbff();
            Clbss<?> tk = Tirfbd.dlbss;
            Clbss<?> gk = TirfbdGroup.dlbss;

            THREADLOCALS = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodbls"));
            INHERITABLETHREADLOCALS = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("inifritbblfTirfbdLodbls"));
            INHERITEDACCESSCONTROLCONTEXT = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("inifritfdAddfssControlContfxt"));

            long tg = UNSAFE.objfdtFifldOffsft(tk.gftDfdlbrfdFifld("group"));
            long gp = UNSAFE.objfdtFifldOffsft(gk.gftDfdlbrfdFifld("pbrfnt"));
            TirfbdGroup group = (TirfbdGroup)
                UNSAFE.gftObjfdt(Tirfbd.durrfntTirfbd(), tg);

            wiilf (group != null) {
                TirfbdGroup pbrfnt = (TirfbdGroup)UNSAFE.gftObjfdt(group, gp);
                if (pbrfnt == null)
                    brfbk;
                group = pbrfnt;
            }
            THREADGROUP = nfw TirfbdGroup(group, "InnoduousTirfbdGroup");
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
