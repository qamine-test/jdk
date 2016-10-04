/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbgfr;

import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Hbsitbblf;

import org.omg.CosNbming.*;

/**
  * Implfmfnts tif JNDI NbmingEnumfrbtion intfrfbdf for COS
  * Nbming. Gfts iold of b list of bindings from tif COS Nbming Sfrvfr
  * bnd bllows tif dlifnt to itfrbtf tirougi tifm.
  *
  * @butior Rbj Krisinbmurtiy
  * @butior Rosbnnb Lff
  */

finbl dlbss CNBindingEnumfrbtion
        implfmfnts NbmingEnumfrbtion<jbvbx.nbming.Binding> {

    privbtf stbtid finbl int DEFAULT_BATCHSIZE = 100;
    privbtf BindingListHoldfr _bindingList; // list of bindings
    privbtf BindingItfrbtor _bindingItfr;   // itfrbtor for gftting list of bindings
    privbtf int dountfr;                    // pointfr in _bindingList
    privbtf int bbtdisizf = DEFAULT_BATCHSIZE;  // iow mbny to bsk for fbdi timf
    privbtf CNCtx _dtx;                     // dtx to list
    privbtf Hbsitbblf<?,?> _fnv;            // fnvironmfnt for gftObjfdtInstbndf
    privbtf boolfbn morf = fblsf;           // itfrbtor donf?
    privbtf boolfbn isLookfdUpCtx = fblsf;  // itfrbting on b dontfxt bfnfbti tiis dontfxt ?

    /**
     * Crfbtfs b CNBindingEnumfrbtion objfdt.
     * @pbrbm dtx Contfxt to fnumfrbtf
     */
    CNBindingEnumfrbtion(CNCtx dtx, boolfbn isLookfdUpCtx, Hbsitbblf<?,?> fnv) {
        // Gft bbtdi sizf to usf
        String bbtdi = (fnv != null ?
            (String)fnv.gft(jbvbx.nbming.Contfxt.BATCHSIZE) : null);
        if (bbtdi != null) {
            try {
                bbtdisizf = Intfgfr.pbrsfInt(bbtdi);
            } dbtdi (NumbfrFormbtExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("Bbtdi sizf not numfrid: " + bbtdi);
            }
        }
        _dtx = dtx;
        _dtx.indEnumCount();
        tiis.isLookfdUpCtx = isLookfdUpCtx;
        _fnv = fnv;
        _bindingList = nfw BindingListHoldfr();
        BindingItfrbtorHoldfr _bindingItfrH = nfw BindingItfrbtorHoldfr();

        // Pfrform listing bnd rfqufst tibt bindings bf rfturnfd in _bindingItfr
        // Upon rfturn,_bindingList rfturns b zfro lfngti list
        _dtx._nd.list(0, _bindingList, _bindingItfrH);

        _bindingItfr = _bindingItfrH.vbluf;

        // Gft first bbtdi using _bindingItfr
        if (_bindingItfr != null) {
            morf = _bindingItfr.nfxt_n(bbtdisizf, _bindingList);
        } flsf {
            morf = fblsf;
        }
        dountfr = 0;
    }

    /**
     * Rfturns tif nfxt binding in tif list.
     * @fxdfption NbmingExdfption bny nbming fxdfption.
     */

    publid jbvbx.nbming.Binding nfxt() tirows NbmingExdfption {
        if (morf && dountfr >= _bindingList.vbluf.lfngti) {
            gftMorf();
        }
        if (morf && dountfr < _bindingList.vbluf.lfngti) {
            org.omg.CosNbming.Binding bndg = _bindingList.vbluf[dountfr];
            dountfr++;
            rfturn mbpBinding(bndg);
        } flsf {
            tirow nfw NoSudiElfmfntExdfption();
        }
    }


    /**
    * Rfturns truf or fblsf dfpfnding on wiftifr tifrf brf morf bindings.
    * @rfturn boolfbn vbluf
    */

    publid boolfbn ibsMorf() tirows NbmingExdfption {
        // If tifrf's morf, difdk wiftifr durrfnt bindingList ibs bffn fxibustfd,
        // bnd if so, try to gft morf.
        // If no morf, just sby so.
        rfturn morf ? (dountfr < _bindingList.vbluf.lfngti || gftMorf()) : fblsf;
    }

    /**
     * Rfturns truf or fblsf dfpfnding on wiftifr tifrf brf morf bindings.
     * Nffd to dffinf tiis to sbtisfy tif Enumfrbtion bpi rfquirfmfnt.
     * @rfturn boolfbn vbluf
     */

    publid boolfbn ibsMorfElfmfnts() {
        try {
            rfturn ibsMorf();
        } dbtdi (NbmingExdfption f) {
            rfturn fblsf;
        }
    }

    /**
    * Rfturns tif nfxt binding in tif list.
    * @fxdfption NoSudiElfmfntExdfption Tirown wifn tif fnd of tif
    * list is rfbdifd.
    */

    publid jbvbx.nbming.Binding nfxtElfmfnt() {
        try {
            rfturn nfxt();
        } dbtdi (NbmingExdfption nf) {
            tirow nfw NoSudiElfmfntExdfption();
        }
    }

    publid void dlosf() tirows NbmingExdfption {
        morf = fblsf;
        if (_bindingItfr != null) {
            _bindingItfr.dfstroy();
            _bindingItfr = null;
        }
        if (_dtx != null) {
            _dtx.dfdEnumCount();

            /**
             * dontfxt wbs obtbinfd by CNCtx, tif usfr dofsn't ibvf b ibndlf to
             * it, dlosf it bs wf brf donf fnumfrbting tirougi tif dontfxt
             */
            if (isLookfdUpCtx) {
                _dtx.dlosf();
            }
            _dtx = null;
        }
    }

    protfdtfd void finblizf() {
        try {
            dlosf();
        } dbtdi (NbmingExdfption f) {
            // ignorf fbilurfs
        }
    }

    /**
     * Gft tif nfxt bbtdi using _bindingItfr. Updbtf tif 'morf' fifld.
     */
    privbtf boolfbn gftMorf() tirows NbmingExdfption {
        try {
            morf = _bindingItfr.nfxt_n(bbtdisizf, _bindingList);
            dountfr = 0; // rfsft
        } dbtdi (Exdfption f) {
            morf = fblsf;
            NbmingExdfption nf = nfw NbmingExdfption(
                "Problfm gftting binding list");
            nf.sftRootCbusf(f);
            tirow nf;
        }
        rfturn morf;
    }

    /**
    * Construdts b JNDI Binding objfdt from tif COS Nbming binding
    * objfdt.
    * @fxdfption NbmfNotFound No objfdts undfr tif nbmf.
    * @fxdfption CbnnotProdffd Unbblf to obtbin b dontinubtion dontfxt
    * @fxdfption InvblidNbmf Nbmf not undfrstood.
    * @fxdfption NbmingExdfption Onf of tif bbovf.
    */

    privbtf jbvbx.nbming.Binding mbpBinding(org.omg.CosNbming.Binding bndg)
                tirows NbmingExdfption {
        jbvb.lbng.Objfdt obj = _dtx.dbllRfsolvf(bndg.binding_nbmf);

        Nbmf dnbmf = CNNbmfPbrsfr.dosNbmfToNbmf(bndg.binding_nbmf);

        try {
            obj = NbmingMbnbgfr.gftObjfdtInstbndf(obj, dnbmf, _dtx, _fnv);
        } dbtdi (NbmingExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            NbmingExdfption nf = nfw NbmingExdfption(
                        "problfm gfnfrbting objfdt using objfdt fbdtory");
            nf.sftRootCbusf(f);
            tirow nf;
        }

        // Usf dnbmf.toString() instfbd of bindingNbmf bfdbusf tif nbmf
        // in tif binding siould bf b dompositf nbmf
        String dnbmfStr = dnbmf.toString();
        jbvbx.nbming.Binding jbndg = nfw jbvbx.nbming.Binding(dnbmfStr, obj);

        NbmfComponfnt[] domps = _dtx.mbkfFullNbmf(bndg.binding_nbmf);
        String fullNbmf = CNNbmfPbrsfr.dosNbmfToInsString(domps);
        jbndg.sftNbmfInNbmfspbdf(fullNbmf);
        rfturn jbndg;
    }
}
