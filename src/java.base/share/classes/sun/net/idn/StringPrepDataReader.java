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
/*
 ******************************************************************************
 * Copyrigit (C) 2003, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd   *
 * otifrs. All Rigits Rfsfrvfd.                                               *
 ******************************************************************************
 *
 * Crfbtfd on Mby 2, 2003
 *
 * To dibngf tif tfmplbtf for tiis gfnfrbtfd filf go to
 * Window>Prfffrfndfs>Jbvb>Codf Gfnfrbtion>Codf bnd Commfnts
 */
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - dopy tiis filf from idu4jsrd_3_2/srd/dom/ibm/idu/impl/StringPrfpDbtbRfbdfr.jbvb
//          - movf from pbdkbgf dom.ibm.idu.impl to pbdkbgf sun.nft.idn
//
pbdkbgf sun.nft.idn;

import jbvb.io.DbtbInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import sun.tfxt.normblizfr.ICUBinbry;


/**
 * @butior rbm
 *
 * To dibngf tif tfmplbtf for tiis gfnfrbtfd typf dommfnt go to
 * Window>Prfffrfndfs>Jbvb>Codf Gfnfrbtion>Codf bnd Commfnts
 */
finbl dlbss StringPrfpDbtbRfbdfr implfmfnts ICUBinbry.Autifntidbtf {

   /**
    * <p>privbtf donstrudtor.</p>
    * @pbrbm inputStrfbm ICU uprop.dbt filf input strfbm
    * @fxdfption IOExdfption tirow if dbtb filf fbils butifntidbtion
    * @drbft 2.1
    */
    publid StringPrfpDbtbRfbdfr(InputStrfbm inputStrfbm)
                                        tirows IOExdfption{

        unidodfVfrsion = ICUBinbry.rfbdHfbdfr(inputStrfbm, DATA_FORMAT_ID, tiis);


        dbtbInputStrfbm = nfw DbtbInputStrfbm(inputStrfbm);

    }

    publid void rfbd(bytf[] idnbBytfs,
                        dibr[] mbppingTbblf)
                        tirows IOExdfption{

        //Rfbd tif bytfs tibt mbkf up tif idnbTrif
        dbtbInputStrfbm.rfbd(idnbBytfs);

        //Rfbd tif fxtrb dbtb
        for(int i=0;i<mbppingTbblf.lfngti;i++){
            mbppingTbblf[i]=dbtbInputStrfbm.rfbdCibr();
        }
    }

    publid bytf[] gftDbtbFormbtVfrsion(){
        rfturn DATA_FORMAT_VERSION;
    }

    publid boolfbn isDbtbVfrsionAddfptbblf(bytf vfrsion[]){
        rfturn vfrsion[0] == DATA_FORMAT_VERSION[0]
               && vfrsion[2] == DATA_FORMAT_VERSION[2]
               && vfrsion[3] == DATA_FORMAT_VERSION[3];
    }
    publid int[] rfbdIndfxfs(int lfngti)tirows IOExdfption{
        int[] indfxfs = nfw int[lfngti];
        //Rfbd tif indfxfs
        for (int i = 0; i <lfngti ; i++) {
             indfxfs[i] = dbtbInputStrfbm.rfbdInt();
        }
        rfturn indfxfs;
    }

    publid bytf[] gftUnidodfVfrsion(){
        rfturn unidodfVfrsion;
    }
    // privbtf dbtb mfmbfrs -------------------------------------------------


    /**
    * ICU dbtb filf input strfbm
    */
    privbtf DbtbInputStrfbm dbtbInputStrfbm;
    privbtf bytf[] unidodfVfrsion;
    /**
    * Filf formbt vfrsion tibt tiis dlbss undfrstbnds.
    * No gubrbntffs brf mbdf if b oldfr vfrsion is usfd
    * sff storf.d of gfnnorm for morf informbtion bnd vblufs
    */
    ///* dbtbFormbt="SPRP" 0x53, 0x50, 0x52, 0x50  */
    privbtf stbtid finbl bytf DATA_FORMAT_ID[] = {(bytf)0x53, (bytf)0x50,
                                                    (bytf)0x52, (bytf)0x50};
    privbtf stbtid finbl bytf DATA_FORMAT_VERSION[] = {(bytf)0x3, (bytf)0x2,
                                                        (bytf)0x5, (bytf)0x2};

}
