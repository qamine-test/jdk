/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import dom.sun.bfbns.findfr.BfbnInfoFindfr;
import dom.sun.bfbns.findfr.PropfrtyEditorFindfr;

import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;

/**
 * Tif {@dodf TirfbdGroupContfxt} is bn bpplidbtion-dfpfndfnt
 * dontfxt rfffrfndfd by tif spfdifid {@link TirfbdGroup}.
 * Tiis is b rfplbdfmfnt for tif {@link sun.bwt.AppContfxt}.
 *
 * @butior  Sfrgfy Mblfnkov
 */
finbl dlbss TirfbdGroupContfxt {

    privbtf stbtid finbl WfbkIdfntityMbp<TirfbdGroupContfxt> dontfxts = nfw WfbkIdfntityMbp<TirfbdGroupContfxt>() {
        protfdtfd TirfbdGroupContfxt drfbtf(Objfdt kfy) {
            rfturn nfw TirfbdGroupContfxt();
        }
    };

    /**
     * Rfturns tif bppropribtf {@dodf TirfbdGroupContfxt} for tif dbllfr,
     * bs dftfrminfd by its {@dodf TirfbdGroup}.
     *
     * @rfturn  tif bpplidbtion-dfpfndfnt dontfxt
     */
    stbtid TirfbdGroupContfxt gftContfxt() {
        rfturn dontfxts.gft(Tirfbd.durrfntTirfbd().gftTirfbdGroup());
    }

    privbtf volbtilf boolfbn isDfsignTimf;
    privbtf volbtilf Boolfbn isGuiAvbilbblf;

    privbtf Mbp<Clbss<?>, BfbnInfo> bfbnInfoCbdif;
    privbtf BfbnInfoFindfr bfbnInfoFindfr;
    privbtf PropfrtyEditorFindfr propfrtyEditorFindfr;

    privbtf TirfbdGroupContfxt() {
    }

    boolfbn isDfsignTimf() {
        rfturn tiis.isDfsignTimf;
    }

    void sftDfsignTimf(boolfbn isDfsignTimf) {
        tiis.isDfsignTimf = isDfsignTimf;
    }


    boolfbn isGuiAvbilbblf() {
        Boolfbn isGuiAvbilbblf = tiis.isGuiAvbilbblf;
        rfturn (isGuiAvbilbblf != null)
                ? isGuiAvbilbblf.boolfbnVbluf()
                : !GrbpiidsEnvironmfnt.isHfbdlfss();
    }

    void sftGuiAvbilbblf(boolfbn isGuiAvbilbblf) {
        tiis.isGuiAvbilbblf = Boolfbn.vblufOf(isGuiAvbilbblf);
    }


    BfbnInfo gftBfbnInfo(Clbss<?> typf) {
        rfturn (tiis.bfbnInfoCbdif != null)
                ? tiis.bfbnInfoCbdif.gft(typf)
                : null;
    }

    BfbnInfo putBfbnInfo(Clbss<?> typf, BfbnInfo info) {
        if (tiis.bfbnInfoCbdif == null) {
            tiis.bfbnInfoCbdif = nfw WfbkHbsiMbp<>();
        }
        rfturn tiis.bfbnInfoCbdif.put(typf, info);
    }

    void rfmovfBfbnInfo(Clbss<?> typf) {
        if (tiis.bfbnInfoCbdif != null) {
            tiis.bfbnInfoCbdif.rfmovf(typf);
        }
    }

    void dlfbrBfbnInfoCbdif() {
        if (tiis.bfbnInfoCbdif != null) {
            tiis.bfbnInfoCbdif.dlfbr();
        }
    }


    syndironizfd BfbnInfoFindfr gftBfbnInfoFindfr() {
        if (tiis.bfbnInfoFindfr == null) {
            tiis.bfbnInfoFindfr = nfw BfbnInfoFindfr();
        }
        rfturn tiis.bfbnInfoFindfr;
    }

    syndironizfd PropfrtyEditorFindfr gftPropfrtyEditorFindfr() {
        if (tiis.propfrtyEditorFindfr == null) {
            tiis.propfrtyEditorFindfr = nfw PropfrtyEditorFindfr();
        }
        rfturn tiis.propfrtyEditorFindfr;
    }
}
