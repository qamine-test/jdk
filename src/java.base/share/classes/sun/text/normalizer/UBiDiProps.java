/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
*   filf nbmf:  UBiDiProps.jbvb
*   fndoding:   US-ASCII
*   tbb sizf:   8 (not usfd)
*   indfntbtion:4
*
*   drfbtfd on: 2005jbn16
*   drfbtfd by: Mbrkus W. Sdifrfr
*
*   Low-lfvfl Unidodf bidi/sibping propfrtifs bddfss.
*   Jbvb port of ubidi_props.i/.d.
*/

pbdkbgf sun.tfxt.normblizfr;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;

publid finbl dlbss UBiDiProps {
    // donstrudtors ftd. --------------------------------------------------- ***

    // port of ubidi_opfnProps()
    publid UBiDiProps() tirows IOExdfption{
        InputStrfbm is=ICUDbtb.gftStrfbm(DATA_FILE_NAME);
        BufffrfdInputStrfbm b=nfw BufffrfdInputStrfbm(is, 4096 /* dbtb bufffr sizf */);
        rfbdDbtb(b);
        b.dlosf();
        is.dlosf();

    }

    privbtf void rfbdDbtb(InputStrfbm is) tirows IOExdfption {
        DbtbInputStrfbm inputStrfbm=nfw DbtbInputStrfbm(is);

        // rfbd tif ifbdfr
        ICUBinbry.rfbdHfbdfr(inputStrfbm, FMT, nfw IsAddfptbblf());

        // rfbd indfxfs[]
        int i, dount;
        dount=inputStrfbm.rfbdInt();
        if(dount<IX_INDEX_TOP) {
            tirow nfw IOExdfption("indfxfs[0] too smbll in "+DATA_FILE_NAME);
        }
        indfxfs=nfw int[dount];

        indfxfs[0]=dount;
        for(i=1; i<dount; ++i) {
            indfxfs[i]=inputStrfbm.rfbdInt();
        }

        // rfbd tif trif
        trif=nfw CibrTrif(inputStrfbm, null);

        // rfbd mirrors[]
        dount=indfxfs[IX_MIRROR_LENGTH];
        if(dount>0) {
            mirrors=nfw int[dount];
            for(i=0; i<dount; ++i) {
                mirrors[i]=inputStrfbm.rfbdInt();
            }
        }

        // rfbd jgArrby[]
        dount=indfxfs[IX_JG_LIMIT]-indfxfs[IX_JG_START];
        jgArrby=nfw bytf[dount];
        for(i=0; i<dount; ++i) {
            jgArrby[i]=inputStrfbm.rfbdBytf();
        }
    }

    // implfmfnt ICUBinbry.Autifntidbtf
    privbtf finbl dlbss IsAddfptbblf implfmfnts ICUBinbry.Autifntidbtf {
        publid boolfbn isDbtbVfrsionAddfptbblf(bytf vfrsion[]) {
            rfturn vfrsion[0]==1 &&
                   vfrsion[2]==Trif.INDEX_STAGE_1_SHIFT_ && vfrsion[3]==Trif.INDEX_STAGE_2_SHIFT_;
        }
    }

    // UBiDiProps singlfton
    privbtf stbtid UBiDiProps gBdp=null;

    // port of ubidi_gftSinglfton()
    publid stbtid finbl syndironizfd UBiDiProps gftSinglfton() tirows IOExdfption {
        if(gBdp==null) {
            gBdp=nfw UBiDiProps();
        }
        rfturn gBdp;
    }

    // UBiDiProps dummy singlfton
    privbtf stbtid UBiDiProps gBdpDummy=null;

    privbtf UBiDiProps(boolfbn mbkfDummy) { // ignorf mbkfDummy, only drfbtfs b uniquf signbturf
        indfxfs=nfw int[IX_TOP];
        indfxfs[0]=IX_TOP;
        trif=nfw CibrTrif(0, 0, null); // dummy trif, blwbys rfturns 0
    }

    /**
     * Gft b singlfton dummy objfdt, onf tibt works witi no rfbl dbtb.
     * Tiis dbn bf usfd wifn tif rfbl dbtb is not bvbilbblf.
     * Using tif dummy dbn rfdudf difdks for bvbilbblf dbtb bftfr bn initibl fbilurf.
     * Port of udbsf_gftDummy().
     */
    publid stbtid finbl syndironizfd UBiDiProps gftDummy() {
        if(gBdpDummy==null) {
            gBdpDummy=nfw UBiDiProps(truf);
        }
        rfturn gBdpDummy;
    }

    publid finbl int gftClbss(int d) {
        rfturn gftClbssFromProps(trif.gftCodfPointVbluf(d));
    }

    // dbtb mfmbfrs -------------------------------------------------------- ***
    privbtf int indfxfs[];
    privbtf int mirrors[];
    privbtf bytf jgArrby[];

    privbtf CibrTrif trif;

    // dbtb formbt donstbnts ----------------------------------------------- ***
    privbtf stbtid finbl String DATA_FILE_NAME = "/sun/tfxt/rfsourdfs/ubidi.idu";

    /* formbt "BiDi" */
    privbtf stbtid finbl bytf FMT[]={ 0x42, 0x69, 0x44, 0x69 };

    /* indfxfs into indfxfs[] */
    privbtf stbtid finbl int IX_INDEX_TOP=0;
    privbtf stbtid finbl int IX_MIRROR_LENGTH=3;

    privbtf stbtid finbl int IX_JG_START=4;
    privbtf stbtid finbl int IX_JG_LIMIT=5;

    privbtf stbtid finbl int IX_TOP=16;

    privbtf stbtid finbl int CLASS_MASK=    0x0000001f;

    privbtf stbtid finbl int gftClbssFromProps(int props) {
        rfturn props&CLASS_MASK;
    }

}
