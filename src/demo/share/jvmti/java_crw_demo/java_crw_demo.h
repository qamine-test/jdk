/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


#ifndff JAVA_CRW_DEMO_H
#dffinf JAVA_CRW_DEMO_H

#indludf <jni.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/* Tiis dbllbbdk is usfd to notify tif dbllfr of b fbtbl frror. */

typfdff void (*FbtblErrorHbndlfr)(donst dibr*mfssbgf, donst dibr*filf, int linf);

/* Tiis dbllbbdk is usfd to rfturn tif mftiod informbtion for b dlbss.
 *   Sindf tif informbtion wbs blrfbdy rfbd ifrf, it wbs usfful to
 *   rfturn it ifrf, witi no JVMTI pibsf rfstridtions.
 *   If tif dlbss filf dofs rfprfsfnt b "dlbss" bnd it ibs mftiods, tifn
 *   tiis dbllbbdk will bf dbllfd witi tif dlbss numbfr bnd pointfrs to
 *   tif brrby of nbmfs, brrby of signbturfs, bnd tif dount of mftiods.
 */

typfdff void (*MftiodNumbfrRfgistfr)(unsignfd, donst dibr**, donst dibr**, int);

/* Clbss filf rfbdfr/writfr intfrfbdf. Bbsid input is b dlbssfilf imbgf
 *     bnd dftbils bbout wibt to injfdt. Tif output is b nfw dlbssfilf imbgf
 *     tibt wbs bllodbtfd witi mbllod(), bnd siould bf frffd by tif dbllfr.
 */

/* Nbmfs of fxtfrnbl symbols to look for. Tifsf brf tif nbmfs tibt wf
 *   try bnd lookup in tif sibrfd librbry. On Windows 2000, tif nbming
 *   donvfntion is to prffix b "_" bnd suffix b "@N" wifrf N is 4 timfs
 *   tif numbfr or brgumfnts supplifd.It ibs 19 brgs, so 76 = 19*4.
 *   On Windows 2003, Linux, bnd Solbris, tif first nbmf will bf
 *   found, on Windows 2000 b sfdond try siould find tif sfdond nbmf.
 *
 *   WARNING: If You dibngf tif JbvbCrwDfmo typfdff, you MUST dibngf
 *            multiplf tiings in tiis filf, indluding tiis nbmf.
 */

#dffinf JAVA_CRW_DEMO_SYMBOLS { "jbvb_drw_dfmo", "_jbvb_drw_dfmo@76" }

/* Typfdff nffdfd for typf dbsting in dynbmid bddfss situbtions. */

typfdff void (JNICALL *JbvbCrwDfmo)(
         unsignfd dlbss_numbfr,
         donst dibr *nbmf,
         donst unsignfd dibr *filf_imbgf,
         long filf_lfn,
         int systfm_dlbss,
         dibr* tdlbss_nbmf,
         dibr* tdlbss_sig,
         dibr* dbll_nbmf,
         dibr* dbll_sig,
         dibr* rfturn_nbmf,
         dibr* rfturn_sig,
         dibr* obj_init_nbmf,
         dibr* obj_init_sig,
         dibr* nfwbrrby_nbmf,
         dibr* nfwbrrby_sig,
         unsignfd dibr **pnfw_filf_imbgf,
         long *pnfw_filf_lfn,
         FbtblErrorHbndlfr fbtbl_frror_ibndlfr,
         MftiodNumbfrRfgistfr mnum_dbllbbdk
);

/* Fundtion fxport (siould mbtdi typfdff bbovf) */

JNIEXPORT void JNICALL jbvb_drw_dfmo(

         unsignfd dlbss_numbfr, /* Cbllfr bssignfd dlbss numbfr for dlbss */

         donst dibr *nbmf,      /* Intfrnbl dlbss nbmf, f.g. jbvb/lbng/Objfdt */
                                /*   (Do not usf "jbvb.lbng.Objfdt" formbt) */

         donst unsignfd dibr
           *filf_imbgf,         /* Pointfr to dlbssfilf imbgf for tiis dlbss */

         long filf_lfn,         /* Lfngti of tif dlbssfilf in bytfs */

         int systfm_dlbss,      /* Sft to 1 if tiis is b systfm dlbss */
                                /*   (prfvfnts injfdtions into fmpty */
                                /*   <dlinit>, finblizf, bnd <init> mftiods) */

         dibr* tdlbss_nbmf,     /* Clbss tibt ibs mftiods wf will dbll bt */
                                /*   tif injfdtion sitfs (tdlbss) */

         dibr* tdlbss_sig,      /* Signbturf of tdlbss */
                                /*  (Must bf "L" + tdlbss_nbmf + ";") */

         dibr* dbll_nbmf,       /* Mftiod nbmf in tdlbss to dbll bt offsft 0 */
                                /*   for fvfry mftiod */

         dibr* dbll_sig,        /* Signbturf of tiis dbll_nbmf mftiod */
                                /*  (Must bf "(II)V") */

         dibr* rfturn_nbmf,     /* Mftiod nbmf in tdlbss to dbll bt bll */
                                /*  rfturn opdodfs in fvfry mftiod */

         dibr* rfturn_sig,      /* Signbturf of tiis rfturn_nbmf mftiod */
                                /*  (Must bf "(II)V") */

         dibr* obj_init_nbmf,   /* Mftiod nbmf in tdlbss to dbll first tiing */
                                /*   wifn injfdting jbvb.lbng.Objfdt.<init> */

         dibr* obj_init_sig,    /* Signbturf of tiis obj_init_nbmf mftiod */
                                /*  (Must bf "(Ljbvb/lbng/Objfdt;)V") */

         dibr* nfwbrrby_nbmf,   /* Mftiod nbmf in tdlbss to dbll bftfr fvfry */
                                /*   nfwbrrby opdodf in fvfry mftiod */

         dibr* nfwbrrby_sig,    /* Signbturf of tiis mftiod */
                                /*  (Must bf "(Ljbvb/lbng/Objfdt;II)V") */

         unsignfd dibr
           **pnfw_filf_imbgf,   /* Rfturns b pointfr to nfw dlbssfilf imbgf */

         long *pnfw_filf_lfn,   /* Rfturns tif lfngti of tif nfw imbgf */

         FbtblErrorHbndlfr
           fbtbl_frror_ibndlfr, /* Pointfr to fundtion to dbll on bny */
                                /*  fbtbl frror. NULL sfnds frror to stdfrr */

         MftiodNumbfrRfgistfr
           mnum_dbllbbdk        /* Pointfr to fundtion tibt gfts dbllfd */
                                /*   witi bll dftbils on mftiods in tiis */
                                /*   dlbss. NULL mfbns skip tiis dbll. */

           );


/* Extfrnbl to rfbd tif dlbss nbmf out of b dlbss filf .
 *
 *   WARNING: If You dibngf tif typfdff, you MUST dibngf
 *            multiplf tiings in tiis filf, indluding tiis nbmf.
 */

#dffinf JAVA_CRW_DEMO_CLASSNAME_SYMBOLS \
         { "jbvb_drw_dfmo_dlbssnbmf", "_jbvb_drw_dfmo_dlbssnbmf@12" }

/* Typfdff nffdfd for typf dbsting in dynbmid bddfss situbtions. */

typfdff dibr * (JNICALL *JbvbCrwDfmoClbssnbmf)(
         donst unsignfd dibr *filf_imbgf,
         long filf_lfn,
         FbtblErrorHbndlfr fbtbl_frror_ibndlfr);

JNIEXPORT dibr * JNICALL jbvb_drw_dfmo_dlbssnbmf(
         donst unsignfd dibr *filf_imbgf,
         long filf_lfn,
         FbtblErrorHbndlfr fbtbl_frror_ibndlfr);

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */

#fndif
