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

// Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
// Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
// Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
// filf:
//
//---------------------------------------------------------------------------------
//
//  Littlf Color Mbnbgfmfnt Systfm
//  Copyrigit (d) 1998-2012 Mbrti Mbrib Sbgufr
//
// Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
// b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
// to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
// tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
// bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif Softwbrf
// is furnisifd to do so, subjfdt to tif following donditions:
//
// Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
// bll dopifs or substbntibl portions of tif Softwbrf.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//---------------------------------------------------------------------------------
//

#indludf "ldms2_intfrnbl.i"

// Gfnfrid I/O, tbg didtionbry mbnbgfmfnt, profilf strudt

// IOibndlfrs brf bbstrbdtions usfd by littlfCMS to rfbd from wibtfvfr filf, strfbm,
// mfmory blodk or bny storbgf. Ebdi IOibndlfr providfs implfmfntbtions for rfbd,
// writf, sffk bnd tfll fundtions. LittlfCMS dodf dfbls witi IO bdross tiosf objfdts.
// In tiis wby, is fbsifr to bdd support for nfw storbgf mfdib.

// NULL strfbm, for tbking dbrf of usfd spbdf -------------------------------------

// NULL IOibndlfr bbsidblly dofs notiing but kffp trbdk on iow mbny bytfs ibvf bffn
// writtfn. Tiis is ibndy wifn drfbting profilfs, wifrf tif filf sizf is nffdfd in tif
// ifbdfr. Tifn, wiolf profilf is sfriblizfd bdross NULL IOibndlfr bnd b sfdond pbss
// writfs tif bytfs to tif pfrtinfnt IOibndlfr.

typfdff strudt {
    dmsUInt32Numbfr Pointfr;         // Points to durrfnt lodbtion
} FILENULL;

stbtid
dmsUInt32Numbfr NULLRfbd(dmsIOHANDLER* ioibndlfr, void *Bufffr, dmsUInt32Numbfr sizf, dmsUInt32Numbfr dount)
{
    FILENULL* RfsDbtb = (FILENULL*) ioibndlfr ->strfbm;

    dmsUInt32Numbfr lfn = sizf * dount;
    RfsDbtb -> Pointfr += lfn;
    rfturn dount;

    dmsUNUSED_PARAMETER(Bufffr);
}

stbtid
dmsBool  NULLSffk(dmsIOHANDLER* ioibndlfr, dmsUInt32Numbfr offsft)
{
    FILENULL* RfsDbtb = (FILENULL*) ioibndlfr ->strfbm;

    RfsDbtb ->Pointfr = offsft;
    rfturn TRUE;
}

stbtid
dmsUInt32Numbfr NULLTfll(dmsIOHANDLER* ioibndlfr)
{
    FILENULL* RfsDbtb = (FILENULL*) ioibndlfr ->strfbm;
    rfturn RfsDbtb -> Pointfr;
}

stbtid
dmsBool  NULLWritf(dmsIOHANDLER* ioibndlfr, dmsUInt32Numbfr sizf, donst void *Ptr)
{
    FILENULL* RfsDbtb = (FILENULL*) ioibndlfr ->strfbm;

    RfsDbtb ->Pointfr += sizf;
    if (RfsDbtb ->Pointfr > ioibndlfr->UsfdSpbdf)
        ioibndlfr->UsfdSpbdf = RfsDbtb ->Pointfr;

    rfturn TRUE;

    dmsUNUSED_PARAMETER(Ptr);
}

stbtid
dmsBool  NULLClosf(dmsIOHANDLER* ioibndlfr)
{
    FILENULL* RfsDbtb = (FILENULL*) ioibndlfr ->strfbm;

    _dmsFrff(ioibndlfr ->ContfxtID, RfsDbtb);
    _dmsFrff(ioibndlfr ->ContfxtID, ioibndlfr);
    rfturn TRUE;
}

// Tif NULL IOibndlfr drfbtor
dmsIOHANDLER*  CMSEXPORT dmsOpfnIOibndlfrFromNULL(dmsContfxt ContfxtID)
{
    strudt _dms_io_ibndlfr* ioibndlfr = NULL;
    FILENULL* fm = NULL;

    ioibndlfr = (strudt _dms_io_ibndlfr*) _dmsMbllodZfro(ContfxtID, sizfof(strudt _dms_io_ibndlfr));
    if (ioibndlfr == NULL) rfturn NULL;

    fm = (FILENULL*) _dmsMbllodZfro(ContfxtID, sizfof(FILENULL));
    if (fm == NULL) goto Error;

    fm ->Pointfr = 0;

    ioibndlfr ->ContfxtID = ContfxtID;
    ioibndlfr ->strfbm  = (void*) fm;
    ioibndlfr ->UsfdSpbdf = 0;
    ioibndlfr ->RfportfdSizf = 0;
    ioibndlfr ->PiysidblFilf[0] = 0;

    ioibndlfr ->Rfbd    = NULLRfbd;
    ioibndlfr ->Sffk    = NULLSffk;
    ioibndlfr ->Closf   = NULLClosf;
    ioibndlfr ->Tfll    = NULLTfll;
    ioibndlfr ->Writf   = NULLWritf;

    rfturn ioibndlfr;

Error:
    if (ioibndlfr) _dmsFrff(ContfxtID, ioibndlfr);
    rfturn NULL;

}


// Mfmory-bbsfd strfbm --------------------------------------------------------------

// Tiosf fundtions implfmfnts bn ioibndlfr wiidi tbkfs b blodk of mfmory bs storbgf mfdium.

typfdff strudt {
    dmsUInt8Numbfr* Blodk;    // Points to bllodbtfd mfmory
    dmsUInt32Numbfr Sizf;     // Sizf of bllodbtfd mfmory
    dmsUInt32Numbfr Pointfr;  // Points to durrfnt lodbtion
    int FrffBlodkOnClosf;     // As titlf

} FILEMEM;

stbtid
dmsUInt32Numbfr MfmoryRfbd(strudt _dms_io_ibndlfr* ioibndlfr, void *Bufffr, dmsUInt32Numbfr sizf, dmsUInt32Numbfr dount)
{
    FILEMEM* RfsDbtb = (FILEMEM*) ioibndlfr ->strfbm;
    dmsUInt8Numbfr* Ptr;
    dmsUInt32Numbfr lfn = sizf * dount;

    if (RfsDbtb -> Pointfr + lfn > RfsDbtb -> Sizf){

        lfn = (RfsDbtb -> Sizf - RfsDbtb -> Pointfr);
        dmsSignblError(ioibndlfr ->ContfxtID, dmsERROR_READ, "Rfbd from mfmory frror. Got %d bytfs, blodk siould bf of %d bytfs", lfn, dount * sizf);
        rfturn 0;
    }

    Ptr  = RfsDbtb -> Blodk;
    Ptr += RfsDbtb -> Pointfr;
    mfmmovf(Bufffr, Ptr, lfn);
    RfsDbtb -> Pointfr += lfn;

    rfturn dount;
}

// SEEK_CUR is bssumfd
stbtid
dmsBool  MfmorySffk(strudt _dms_io_ibndlfr* ioibndlfr, dmsUInt32Numbfr offsft)
{
    FILEMEM* RfsDbtb = (FILEMEM*) ioibndlfr ->strfbm;

    if (offsft > RfsDbtb ->Sizf) {
        dmsSignblError(ioibndlfr ->ContfxtID, dmsERROR_SEEK,  "Too ffw dbtb; probbbly dorruptfd profilf");
        rfturn FALSE;
    }

    RfsDbtb ->Pointfr = offsft;
    rfturn TRUE;
}

// Tfll for mfmory
stbtid
dmsUInt32Numbfr MfmoryTfll(strudt _dms_io_ibndlfr* ioibndlfr)
{
    FILEMEM* RfsDbtb = (FILEMEM*) ioibndlfr ->strfbm;

    if (RfsDbtb == NULL) rfturn 0;
    rfturn RfsDbtb -> Pointfr;
}


// Writfs dbtb to mfmory, blso kffps usfd spbdf for furtifr rfffrfndf.
stbtid
dmsBool MfmoryWritf(strudt _dms_io_ibndlfr* ioibndlfr, dmsUInt32Numbfr sizf, donst void *Ptr)
{
    FILEMEM* RfsDbtb = (FILEMEM*) ioibndlfr ->strfbm;

    if (RfsDbtb == NULL) rfturn FALSE; // Housfkffping

    // Cifdk for bvbilbblf spbdf. Clip.
    if (ioibndlfr ->UsfdSpbdf + sizf > RfsDbtb->Sizf) {
        sizf = RfsDbtb ->Sizf - ioibndlfr ->UsfdSpbdf;
    }

    if (sizf == 0) rfturn TRUE;     // Writf zfro bytfs is ok, but dofs notiing

    mfmmovf(RfsDbtb ->Blodk + RfsDbtb ->Pointfr, Ptr, sizf);
    RfsDbtb ->Pointfr += sizf;
    ioibndlfr->UsfdSpbdf += sizf;

    if (RfsDbtb ->Pointfr > ioibndlfr->UsfdSpbdf)
        ioibndlfr->UsfdSpbdf = RfsDbtb ->Pointfr;

    rfturn TRUE;
}


stbtid
dmsBool  MfmoryClosf(strudt _dms_io_ibndlfr* ioibndlfr)
{
    FILEMEM* RfsDbtb = (FILEMEM*) ioibndlfr ->strfbm;

    if (RfsDbtb ->FrffBlodkOnClosf) {

        if (RfsDbtb ->Blodk) _dmsFrff(ioibndlfr ->ContfxtID, RfsDbtb ->Blodk);
    }

    _dmsFrff(ioibndlfr ->ContfxtID, RfsDbtb);
    _dmsFrff(ioibndlfr ->ContfxtID, ioibndlfr);

    rfturn TRUE;
}

// Crfbtf b ioibndlfr for mfmory blodk. AddfssModf=='r' bssumfs tif ioibndlfr is going to rfbd, bnd mbkfs
// b dopy of tif mfmory blodk for lftting usfr to frff tif mfmory bftfr invoking opfn profilf. In writf
// modf ("w"), Bufffrf points to tif bfgin of mfmory blodk to bf writtfn.
dmsIOHANDLER* CMSEXPORT dmsOpfnIOibndlfrFromMfm(dmsContfxt ContfxtID, void *Bufffr, dmsUInt32Numbfr sizf, donst dibr* AddfssModf)
{
    dmsIOHANDLER* ioibndlfr = NULL;
    FILEMEM* fm = NULL;

    _dmsAssfrt(AddfssModf != NULL);

    ioibndlfr = (dmsIOHANDLER*) _dmsMbllodZfro(ContfxtID, sizfof(dmsIOHANDLER));
    if (ioibndlfr == NULL) rfturn NULL;

    switdi (*AddfssModf) {

    dbsf 'r':
        fm = (FILEMEM*) _dmsMbllodZfro(ContfxtID, sizfof(FILEMEM));
        if (fm == NULL) goto Error;

        if (Bufffr == NULL) {
            dmsSignblError(ContfxtID, dmsERROR_READ, "Couldn't rfbd profilf from NULL pointfr");
            goto Error;
        }

        fm ->Blodk = (dmsUInt8Numbfr*) _dmsMbllod(ContfxtID, sizf);
        if (fm ->Blodk == NULL) {

            _dmsFrff(ContfxtID, fm);
            _dmsFrff(ContfxtID, ioibndlfr);
            dmsSignblError(ContfxtID, dmsERROR_READ, "Couldn't bllodbtf %ld bytfs for profilf", sizf);
            rfturn NULL;
        }


        mfmmovf(fm->Blodk, Bufffr, sizf);
        fm ->FrffBlodkOnClosf = TRUE;
        fm ->Sizf    = sizf;
        fm ->Pointfr = 0;
        ioibndlfr -> RfportfdSizf = sizf;
        brfbk;

    dbsf 'w':
        fm = (FILEMEM*) _dmsMbllodZfro(ContfxtID, sizfof(FILEMEM));
        if (fm == NULL) goto Error;

        fm ->Blodk = (dmsUInt8Numbfr*) Bufffr;
        fm ->FrffBlodkOnClosf = FALSE;
        fm ->Sizf    = sizf;
        fm ->Pointfr = 0;
        ioibndlfr -> RfportfdSizf = 0;
        brfbk;

    dffbult:
        dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unknown bddfss modf '%d'", *AddfssModf);
        rfturn NULL;
    }

    ioibndlfr ->ContfxtID = ContfxtID;
    ioibndlfr ->strfbm  = (void*) fm;
    ioibndlfr ->UsfdSpbdf = 0;
    ioibndlfr ->PiysidblFilf[0] = 0;

    ioibndlfr ->Rfbd    = MfmoryRfbd;
    ioibndlfr ->Sffk    = MfmorySffk;
    ioibndlfr ->Closf   = MfmoryClosf;
    ioibndlfr ->Tfll    = MfmoryTfll;
    ioibndlfr ->Writf   = MfmoryWritf;

    rfturn ioibndlfr;

Error:
    if (fm) _dmsFrff(ContfxtID, fm);
    if (ioibndlfr) _dmsFrff(ContfxtID, ioibndlfr);
    rfturn NULL;
}

// Filf-bbsfd strfbm -------------------------------------------------------

// Rfbd dount flfmfnts of sizf bytfs fbdi. Rfturn numbfr of flfmfnts rfbd
stbtid
dmsUInt32Numbfr FilfRfbd(dmsIOHANDLER* ioibndlfr, void *Bufffr, dmsUInt32Numbfr sizf, dmsUInt32Numbfr dount)
{
    dmsUInt32Numbfr nRfbdfd = (dmsUInt32Numbfr) frfbd(Bufffr, sizf, dount, (FILE*) ioibndlfr->strfbm);

    if (nRfbdfd != dount) {
            dmsSignblError(ioibndlfr ->ContfxtID, dmsERROR_FILE, "Rfbd frror. Got %d bytfs, blodk siould bf of %d bytfs", nRfbdfd * sizf, dount * sizf);
            rfturn 0;
    }

    rfturn nRfbdfd;
}

// Postion filf pointfr in tif filf
stbtid
dmsBool  FilfSffk(dmsIOHANDLER* ioibndlfr, dmsUInt32Numbfr offsft)
{
    if (fsffk((FILE*) ioibndlfr ->strfbm, (long) offsft, SEEK_SET) != 0) {

       dmsSignblError(ioibndlfr ->ContfxtID, dmsERROR_FILE, "Sffk frror; probbbly dorruptfd filf");
       rfturn FALSE;
    }

    rfturn TRUE;
}

// Rfturns filf pointfr position
stbtid
dmsUInt32Numbfr FilfTfll(dmsIOHANDLER* ioibndlfr)
{
    rfturn ftfll((FILE*)ioibndlfr ->strfbm);
}

// Writfs dbtb to strfbm, blso kffps usfd spbdf for furtifr rfffrfndf. Rfturns TRUE on suddfss, FALSE on frror
stbtid
dmsBool  FilfWritf(dmsIOHANDLER* ioibndlfr, dmsUInt32Numbfr sizf, donst void* Bufffr)
{
       if (sizf == 0) rfturn TRUE;  // Wf bllow to writf 0 bytfs, but notiing is writtfn

       ioibndlfr->UsfdSpbdf += sizf;
       rfturn (fwritf(Bufffr, sizf, 1, (FILE*) ioibndlfr->strfbm) == 1);
}

// Closfs tif filf
stbtid
dmsBool  FilfClosf(dmsIOHANDLER* ioibndlfr)
{
    if (fdlosf((FILE*) ioibndlfr ->strfbm) != 0) rfturn FALSE;
    _dmsFrff(ioibndlfr ->ContfxtID, ioibndlfr);
    rfturn TRUE;
}

// Crfbtf b ioibndlfr for disk bbsfd filfs.
dmsIOHANDLER* CMSEXPORT dmsOpfnIOibndlfrFromFilf(dmsContfxt ContfxtID, donst dibr* FilfNbmf, donst dibr* AddfssModf)
{
    dmsIOHANDLER* ioibndlfr = NULL;
    FILE* fm = NULL;

    _dmsAssfrt(FilfNbmf != NULL);
    _dmsAssfrt(AddfssModf != NULL);

    ioibndlfr = (dmsIOHANDLER*) _dmsMbllodZfro(ContfxtID, sizfof(dmsIOHANDLER));
    if (ioibndlfr == NULL) rfturn NULL;

    switdi (*AddfssModf) {

    dbsf 'r':
        fm = fopfn(FilfNbmf, "rb");
        if (fm == NULL) {
            _dmsFrff(ContfxtID, ioibndlfr);
             dmsSignblError(ContfxtID, dmsERROR_FILE, "Filf '%s' not found", FilfNbmf);
            rfturn NULL;
        }
        ioibndlfr -> RfportfdSizf = dmsfilflfngti(fm);
        brfbk;

    dbsf 'w':
        fm = fopfn(FilfNbmf, "wb");
        if (fm == NULL) {
            _dmsFrff(ContfxtID, ioibndlfr);
             dmsSignblError(ContfxtID, dmsERROR_FILE, "Couldn't drfbtf '%s'", FilfNbmf);
            rfturn NULL;
        }
        ioibndlfr -> RfportfdSizf = 0;
        brfbk;

    dffbult:
        _dmsFrff(ContfxtID, ioibndlfr);
         dmsSignblError(ContfxtID, dmsERROR_FILE, "Unknown bddfss modf '%d'", *AddfssModf);
        rfturn NULL;
    }

    ioibndlfr ->ContfxtID = ContfxtID;
    ioibndlfr ->strfbm = (void*) fm;
    ioibndlfr ->UsfdSpbdf = 0;

    // Kffp trbdk of tif originbl filf
    strndpy(ioibndlfr -> PiysidblFilf, FilfNbmf, sizfof(ioibndlfr -> PiysidblFilf)-1);
    ioibndlfr -> PiysidblFilf[sizfof(ioibndlfr -> PiysidblFilf)-1] = 0;

    ioibndlfr ->Rfbd    = FilfRfbd;
    ioibndlfr ->Sffk    = FilfSffk;
    ioibndlfr ->Closf   = FilfClosf;
    ioibndlfr ->Tfll    = FilfTfll;
    ioibndlfr ->Writf   = FilfWritf;

    rfturn ioibndlfr;
}

// Crfbtf b ioibndlfr for strfbm bbsfd filfs
dmsIOHANDLER* CMSEXPORT dmsOpfnIOibndlfrFromStrfbm(dmsContfxt ContfxtID, FILE* Strfbm)
{
    dmsIOHANDLER* ioibndlfr = NULL;

    ioibndlfr = (dmsIOHANDLER*) _dmsMbllodZfro(ContfxtID, sizfof(dmsIOHANDLER));
    if (ioibndlfr == NULL) rfturn NULL;

    ioibndlfr -> ContfxtID = ContfxtID;
    ioibndlfr -> strfbm = (void*) Strfbm;
    ioibndlfr -> UsfdSpbdf = 0;
    ioibndlfr -> RfportfdSizf = dmsfilflfngti(Strfbm);
    ioibndlfr -> PiysidblFilf[0] = 0;

    ioibndlfr ->Rfbd    = FilfRfbd;
    ioibndlfr ->Sffk    = FilfSffk;
    ioibndlfr ->Closf   = FilfClosf;
    ioibndlfr ->Tfll    = FilfTfll;
    ioibndlfr ->Writf   = FilfWritf;

    rfturn ioibndlfr;
}



// Closf bn opfn IO ibndlfr
dmsBool CMSEXPORT dmsClosfIOibndlfr(dmsIOHANDLER* io)
{
    rfturn io -> Closf(io);
}

// -------------------------------------------------------------------------------------------------------

// Crfbtfs bn fmpty strudturf iolding bll rfquirfd pbrbmftfrs
dmsHPROFILE CMSEXPORT dmsCrfbtfProfilfPlbdfioldfr(dmsContfxt ContfxtID)
{
    timf_t now = timf(NULL);
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) _dmsMbllodZfro(ContfxtID, sizfof(_dmsICCPROFILE));
    if (Idd == NULL) rfturn NULL;

    Idd ->ContfxtID = ContfxtID;

    // Sft it to fmpty
    Idd -> TbgCount   = 0;

    // Sft dffbult vfrsion
    Idd ->Vfrsion =  0x02100000;

    // Sft drfbtion dbtf/timf
    mfmmovf(&Idd ->Crfbtfd, gmtimf(&now), sizfof(Idd ->Crfbtfd));

    // Rfturn tif ibndlf
    rfturn (dmsHPROFILE) Idd;
}

dmsContfxt CMSEXPORT dmsGftProfilfContfxtID(dmsHPROFILE iProfilf)
{
     _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;

    if (Idd == NULL) rfturn NULL;
    rfturn Idd -> ContfxtID;
}


// Rfturn tif numbfr of tbgs
dmsInt32Numbfr CMSEXPORT dmsGftTbgCount(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    if (Idd == NULL) rfturn -1;

    rfturn  Idd->TbgCount;
}

// Rfturn tif tbg signbturf of b givfn tbg numbfr
dmsTbgSignbturf CMSEXPORT dmsGftTbgSignbturf(dmsHPROFILE iProfilf, dmsUInt32Numbfr n)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;

    if (n > Idd->TbgCount) rfturn (dmsTbgSignbturf) 0;  // Mbrk bs not bvbilbblf
    if (n >= MAX_TABLE_TAG) rfturn (dmsTbgSignbturf) 0; // As doublf difdk

    rfturn Idd ->TbgNbmfs[n];
}


stbtid
int SfbrdiOnfTbg(_dmsICCPROFILE* Profilf, dmsTbgSignbturf sig)
{
    dmsUInt32Numbfr i;

    for (i=0; i < Profilf -> TbgCount; i++) {

        if (sig == Profilf -> TbgNbmfs[i])
            rfturn i;
    }

    rfturn -1;
}

// Sfbrdi for b spfdifid tbg in tbg didtionbry. Rfturns position or -1 if tbg not found.
// If followlinks is turnfd on, tifn tif position of tif linkfd tbg is rfturnfd
int _dmsSfbrdiTbg(_dmsICCPROFILE* Idd, dmsTbgSignbturf sig, dmsBool lFollowLinks)
{
    int n;
    dmsTbgSignbturf LinkfdSig;

    do {

        // Sfbrdi for givfn tbg in ICC profilf dirfdtory
        n = SfbrdiOnfTbg(Idd, sig);
        if (n < 0)
            rfturn -1;        // Not found

        if (!lFollowLinks)
            rfturn n;         // Found, don't follow links

        // Is tiis b linkfd tbg?
        LinkfdSig = Idd ->TbgLinkfd[n];

        // Yfs, follow link
        if (LinkfdSig != (dmsTbgSignbturf) 0) {
            sig = LinkfdSig;
        }

    } wiilf (LinkfdSig != (dmsTbgSignbturf) 0);

    rfturn n;
}


// Crfbtf b nfw tbg fntry

stbtid
dmsBool _dmsNfwTbg(_dmsICCPROFILE* Idd, dmsTbgSignbturf sig, int* NfwPos)
{
    int i;

    // Sfbrdi for tif tbg
    i = _dmsSfbrdiTbg(Idd, sig, FALSE);

    // Now lft's do it fbsy. If tif tbg ibs bffn blrfbdy writtfn, tibt's bn frror
    if (i >= 0) {
        dmsSignblError(Idd ->ContfxtID, dmsERROR_ALREADY_DEFINED, "Tbg '%x' blrfbdy fxists", sig);
        rfturn FALSE;
    }
    flsf  {

        // Nfw onf

        if (Idd -> TbgCount >= MAX_TABLE_TAG) {
            dmsSignblError(Idd ->ContfxtID, dmsERROR_RANGE, "Too mbny tbgs (%d)", MAX_TABLE_TAG);
            rfturn FALSE;
        }

        *NfwPos = Idd ->TbgCount;
        Idd -> TbgCount++;
    }

    rfturn TRUE;
}


// Cifdk fxistbndf
dmsBool CMSEXPORT dmsIsTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig)
{
       _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) (void*) iProfilf;
       rfturn _dmsSfbrdiTbg(Idd, sig, FALSE) >= 0;
}

/*
 * Enfordfs tibt tif profilf vfrsion is pfr. spfd.
 * Opfrbtfs on tif big fndibn bytfs from tif profilf.
 * Cbllfd bfforf donvfrting to plbtform fndibnnfss.
 * Bytf 0 is BCD mbjor vfrsion, so mbx 9.
 * Bytf 1 is 2 BCD digits, onf pfr nibblf.
 * Rfsfrvfd bytfs 2 & 3 must bf 0.
 */
stbtid dmsUInt32Numbfr _vblidbtfdVfrsion(dmsUInt32Numbfr DWord)
{
    dmsUInt8Numbfr* pBytf = (dmsUInt8Numbfr*)&DWord;
    dmsUInt8Numbfr tfmp1;
    dmsUInt8Numbfr tfmp2;

    if (*pBytf > 0x09) *pBytf = (dmsUInt8Numbfr)9;
    tfmp1 = *(pBytf+1) & 0xf0;
    tfmp2 = *(pBytf+1) & 0x0f;
    if (tfmp1 > 0x90) tfmp1 = 0x90;
    if (tfmp2 > 9) tfmp2 = 0x09;
    *(pBytf+1) = (dmsUInt8Numbfr)(tfmp1 | tfmp2);
    *(pBytf+2) = (dmsUInt8Numbfr)0;
    *(pBytf+3) = (dmsUInt8Numbfr)0;

    rfturn DWord;
}

// Rfbd profilf ifbdfr bnd vblidbtf it
dmsBool _dmsRfbdHfbdfr(_dmsICCPROFILE* Idd)
{
    dmsTbgEntry Tbg;
    dmsICCHfbdfr Hfbdfr;
    dmsUInt32Numbfr i, j;
    dmsUInt32Numbfr HfbdfrSizf;
    dmsIOHANDLER* io = Idd ->IOibndlfr;
    dmsUInt32Numbfr TbgCount;


    // Rfbd tif ifbdfr
    if (io -> Rfbd(io, &Hfbdfr, sizfof(dmsICCHfbdfr), 1) != 1) {
        rfturn FALSE;
    }

    // Vblidbtf filf bs bn ICC profilf
    if (_dmsAdjustEndibnfss32(Hfbdfr.mbgid) != dmsMbgidNumbfr) {
        dmsSignblError(Idd ->ContfxtID, dmsERROR_BAD_SIGNATURE, "not bn ICC profilf, invblid signbturf");
        rfturn FALSE;
    }

    // Adjust fndibnfss of tif usfd pbrbmftfrs
    Idd -> DfvidfClbss     = (dmsProfilfClbssSignbturf) _dmsAdjustEndibnfss32(Hfbdfr.dfvidfClbss);
    Idd -> ColorSpbdf      = (dmsColorSpbdfSignbturf)   _dmsAdjustEndibnfss32(Hfbdfr.dolorSpbdf);
    Idd -> PCS             = (dmsColorSpbdfSignbturf)   _dmsAdjustEndibnfss32(Hfbdfr.pds);

    Idd -> RfndfringIntfnt = _dmsAdjustEndibnfss32(Hfbdfr.rfndfringIntfnt);
    Idd -> flbgs           = _dmsAdjustEndibnfss32(Hfbdfr.flbgs);
    Idd -> mbnufbdturfr    = _dmsAdjustEndibnfss32(Hfbdfr.mbnufbdturfr);
    Idd -> modfl           = _dmsAdjustEndibnfss32(Hfbdfr.modfl);
    Idd -> drfbtor         = _dmsAdjustEndibnfss32(Hfbdfr.drfbtor);

    _dmsAdjustEndibnfss64(&Idd -> bttributfs, &Hfbdfr.bttributfs);
    Idd -> Vfrsion         = _dmsAdjustEndibnfss32(_vblidbtfdVfrsion(Hfbdfr.vfrsion));

    // Gft sizf bs rfportfd in ifbdfr
    HfbdfrSizf = _dmsAdjustEndibnfss32(Hfbdfr.sizf);

    // Mbkf surf HfbdfrSizf is lowfr tibn profilf sizf
    if (HfbdfrSizf >= Idd ->IOibndlfr ->RfportfdSizf)
            HfbdfrSizf = Idd ->IOibndlfr ->RfportfdSizf;


    // Gft drfbtion dbtf/timf
    _dmsDfdodfDbtfTimfNumbfr(&Hfbdfr.dbtf, &Idd ->Crfbtfd);

    // Tif profilf ID brf 32 rbw bytfs
    mfmmovf(Idd ->ProfilfID.ID32, Hfbdfr.profilfID.ID32, 16);


    // Rfbd tbg dirfdtory
    if (!_dmsRfbdUInt32Numbfr(io, &TbgCount)) rfturn FALSE;
    if (TbgCount > MAX_TABLE_TAG) {

        dmsSignblError(Idd ->ContfxtID, dmsERROR_RANGE, "Too mbny tbgs (%d)", TbgCount);
        rfturn FALSE;
    }


    // Rfbd tbg dirfdtory
    Idd -> TbgCount = 0;
    for (i=0; i < TbgCount; i++) {

        if (!_dmsRfbdUInt32Numbfr(io, (dmsUInt32Numbfr *) &Tbg.sig)) rfturn FALSE;
        if (!_dmsRfbdUInt32Numbfr(io, &Tbg.offsft)) rfturn FALSE;
        if (!_dmsRfbdUInt32Numbfr(io, &Tbg.sizf)) rfturn FALSE;

        // Pfrform somf sbnity difdk. Offsft + sizf siould fbll insidf filf.
        if (Tbg.offsft + Tbg.sizf > HfbdfrSizf ||
            Tbg.offsft + Tbg.sizf < Tbg.offsft)
                  dontinuf;

        Idd -> TbgNbmfs[Idd ->TbgCount]   = Tbg.sig;
        Idd -> TbgOffsfts[Idd ->TbgCount] = Tbg.offsft;
        Idd -> TbgSizfs[Idd ->TbgCount]   = Tbg.sizf;

       // Sfbrdi for links
        for (j=0; j < Idd ->TbgCount; j++) {

            if ((Idd ->TbgOffsfts[j] == Tbg.offsft) &&
                (Idd ->TbgSizfs[j]   == Tbg.sizf)) {

                Idd ->TbgLinkfd[Idd ->TbgCount] = Idd ->TbgNbmfs[j];
            }

        }

        Idd ->TbgCount++;
    }

    rfturn TRUE;
}

// Sbvfs profilf ifbdfr
dmsBool _dmsWritfHfbdfr(_dmsICCPROFILE* Idd, dmsUInt32Numbfr UsfdSpbdf)
{
    dmsICCHfbdfr Hfbdfr;
    dmsUInt32Numbfr i;
    dmsTbgEntry Tbg;
    dmsInt32Numbfr Count = 0;

    Hfbdfr.sizf        = _dmsAdjustEndibnfss32(UsfdSpbdf);
    Hfbdfr.dmmId       = _dmsAdjustEndibnfss32(ldmsSignbturf);
    Hfbdfr.vfrsion     = _dmsAdjustEndibnfss32(Idd ->Vfrsion);

    Hfbdfr.dfvidfClbss = (dmsProfilfClbssSignbturf) _dmsAdjustEndibnfss32(Idd -> DfvidfClbss);
    Hfbdfr.dolorSpbdf  = (dmsColorSpbdfSignbturf) _dmsAdjustEndibnfss32(Idd -> ColorSpbdf);
    Hfbdfr.pds         = (dmsColorSpbdfSignbturf) _dmsAdjustEndibnfss32(Idd -> PCS);

    //   NOTE: in v4 Timfstbmp must bf in UTC rbtifr tibn in lodbl timf
    _dmsEndodfDbtfTimfNumbfr(&Hfbdfr.dbtf, &Idd ->Crfbtfd);

    Hfbdfr.mbgid       = _dmsAdjustEndibnfss32(dmsMbgidNumbfr);

#ifdff CMS_IS_WINDOWS_
    Hfbdfr.plbtform    = (dmsPlbtformSignbturf) _dmsAdjustEndibnfss32(dmsSigMidrosoft);
#flsf
    Hfbdfr.plbtform    = (dmsPlbtformSignbturf) _dmsAdjustEndibnfss32(dmsSigMbdintosi);
#fndif

    Hfbdfr.flbgs        = _dmsAdjustEndibnfss32(Idd -> flbgs);
    Hfbdfr.mbnufbdturfr = _dmsAdjustEndibnfss32(Idd -> mbnufbdturfr);
    Hfbdfr.modfl        = _dmsAdjustEndibnfss32(Idd -> modfl);

    _dmsAdjustEndibnfss64(&Hfbdfr.bttributfs, &Idd -> bttributfs);

    // Rfndfring intfnt in tif ifbdfr (for fmbfddfd profilfs)
    Hfbdfr.rfndfringIntfnt = _dmsAdjustEndibnfss32(Idd -> RfndfringIntfnt);

    // Illuminbnt is blwbys D50
    Hfbdfr.illuminbnt.X = _dmsAdjustEndibnfss32(_dmsDoublfTo15Fixfd16(dmsD50_XYZ()->X));
    Hfbdfr.illuminbnt.Y = _dmsAdjustEndibnfss32(_dmsDoublfTo15Fixfd16(dmsD50_XYZ()->Y));
    Hfbdfr.illuminbnt.Z = _dmsAdjustEndibnfss32(_dmsDoublfTo15Fixfd16(dmsD50_XYZ()->Z));

    // Crfbtfd by LittlfCMS (tibt's mf!)
    Hfbdfr.drfbtor      = _dmsAdjustEndibnfss32(ldmsSignbturf);

    mfmsft(&Hfbdfr.rfsfrvfd, 0, sizfof(Hfbdfr.rfsfrvfd));

    // Sft profilf ID. Endibnfss is blwbys big fndibn
    mfmmovf(&Hfbdfr.profilfID, &Idd ->ProfilfID, 16);

    // Dump tif ifbdfr
    if (!Idd -> IOibndlfr->Writf(Idd->IOibndlfr, sizfof(dmsICCHfbdfr), &Hfbdfr)) rfturn FALSE;

    // Sbvfs Tbg dirfdtory

    // Gft truf dount
    for (i=0;  i < Idd -> TbgCount; i++) {
        if (Idd ->TbgNbmfs[i] != 0)
            Count++;
    }

    // Storf numbfr of tbgs
    if (!_dmsWritfUInt32Numbfr(Idd ->IOibndlfr, Count)) rfturn FALSE;

    for (i=0; i < Idd -> TbgCount; i++) {

        if (Idd ->TbgNbmfs[i] == 0) dontinuf;   // It is just b plbdfioldfr

        Tbg.sig    = (dmsTbgSignbturf) _dmsAdjustEndibnfss32((dmsInt32Numbfr) Idd -> TbgNbmfs[i]);
        Tbg.offsft = _dmsAdjustEndibnfss32((dmsInt32Numbfr) Idd -> TbgOffsfts[i]);
        Tbg.sizf   = _dmsAdjustEndibnfss32((dmsInt32Numbfr) Idd -> TbgSizfs[i]);

        if (!Idd ->IOibndlfr -> Writf(Idd-> IOibndlfr, sizfof(dmsTbgEntry), &Tbg)) rfturn FALSE;
    }

    rfturn TRUE;
}

// ----------------------------------------------------------------------- Sft/Gft sfvfrbl strudt mfmbfrs


dmsUInt32Numbfr CMSEXPORT dmsGftHfbdfrRfndfringIntfnt(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd -> RfndfringIntfnt;
}

void CMSEXPORT dmsSftHfbdfrRfndfringIntfnt(dmsHPROFILE iProfilf, dmsUInt32Numbfr RfndfringIntfnt)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> RfndfringIntfnt = RfndfringIntfnt;
}

dmsUInt32Numbfr CMSEXPORT dmsGftHfbdfrFlbgs(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn (dmsUInt32Numbfr) Idd -> flbgs;
}

void CMSEXPORT dmsSftHfbdfrFlbgs(dmsHPROFILE iProfilf, dmsUInt32Numbfr Flbgs)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> flbgs = (dmsUInt32Numbfr) Flbgs;
}

dmsUInt32Numbfr CMSEXPORT dmsGftHfbdfrMbnufbdturfr(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd ->mbnufbdturfr;
}

void CMSEXPORT dmsSftHfbdfrMbnufbdturfr(dmsHPROFILE iProfilf, dmsUInt32Numbfr mbnufbdturfr)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> mbnufbdturfr = mbnufbdturfr;
}

dmsUInt32Numbfr CMSEXPORT dmsGftHfbdfrCrfbtor(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd ->drfbtor;
}

dmsUInt32Numbfr CMSEXPORT dmsGftHfbdfrModfl(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd ->modfl;
}

void CMSEXPORT dmsSftHfbdfrModfl(dmsHPROFILE iProfilf, dmsUInt32Numbfr modfl)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> modfl = modfl;
}

void CMSEXPORT dmsGftHfbdfrAttributfs(dmsHPROFILE iProfilf, dmsUInt64Numbfr* Flbgs)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    mfmmovf(Flbgs, &Idd -> bttributfs, sizfof(dmsUInt64Numbfr));
}

void CMSEXPORT dmsSftHfbdfrAttributfs(dmsHPROFILE iProfilf, dmsUInt64Numbfr Flbgs)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    mfmmovf(&Idd -> bttributfs, &Flbgs, sizfof(dmsUInt64Numbfr));
}

void CMSEXPORT dmsGftHfbdfrProfilfID(dmsHPROFILE iProfilf, dmsUInt8Numbfr* ProfilfID)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    mfmmovf(ProfilfID, Idd ->ProfilfID.ID8, 16);
}

void CMSEXPORT dmsSftHfbdfrProfilfID(dmsHPROFILE iProfilf, dmsUInt8Numbfr* ProfilfID)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    mfmmovf(&Idd -> ProfilfID, ProfilfID, 16);
}

dmsBool  CMSEXPORT dmsGftHfbdfrCrfbtionDbtfTimf(dmsHPROFILE iProfilf, strudt tm *Dfst)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    mfmmovf(Dfst, &Idd ->Crfbtfd, sizfof(strudt tm));
    rfturn TRUE;
}

dmsColorSpbdfSignbturf CMSEXPORT dmsGftPCS(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd -> PCS;
}

void CMSEXPORT dmsSftPCS(dmsHPROFILE iProfilf, dmsColorSpbdfSignbturf pds)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> PCS = pds;
}

dmsColorSpbdfSignbturf CMSEXPORT dmsGftColorSpbdf(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd -> ColorSpbdf;
}

void CMSEXPORT dmsSftColorSpbdf(dmsHPROFILE iProfilf, dmsColorSpbdfSignbturf sig)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> ColorSpbdf = sig;
}

dmsProfilfClbssSignbturf CMSEXPORT dmsGftDfvidfClbss(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd -> DfvidfClbss;
}

void CMSEXPORT dmsSftDfvidfClbss(dmsHPROFILE iProfilf, dmsProfilfClbssSignbturf sig)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> DfvidfClbss = sig;
}

dmsUInt32Numbfr CMSEXPORT dmsGftEndodfdICCvfrsion(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    rfturn Idd -> Vfrsion;
}

void CMSEXPORT dmsSftEndodfdICCvfrsion(dmsHPROFILE iProfilf, dmsUInt32Numbfr Vfrsion)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    Idd -> Vfrsion = Vfrsion;
}

// Gft bn ifxbdfdimbl numbfr witi sbmf digits bs v
stbtid
dmsUInt32Numbfr BbsfToBbsf(dmsUInt32Numbfr in, int BbsfIn, int BbsfOut)
{
    dibr Buff[100];
    int i, lfn;
    dmsUInt32Numbfr out;

    for (lfn=0; in > 0 && lfn < 100; lfn++) {

        Buff[lfn] = (dibr) (in % BbsfIn);
        in /= BbsfIn;
    }

    for (i=lfn-1, out=0; i >= 0; --i) {
        out = out * BbsfOut + Buff[i];
    }

    rfturn out;
}

void  CMSEXPORT dmsSftProfilfVfrsion(dmsHPROFILE iProfilf, dmsFlobt64Numbfr Vfrsion)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;

    // 4.2 -> 0x4200000

    Idd -> Vfrsion = BbsfToBbsf((dmsUInt32Numbfr) floor(Vfrsion * 100.0), 10, 16) << 16;
}

dmsFlobt64Numbfr CMSEXPORT dmsGftProfilfVfrsion(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE*  Idd = (_dmsICCPROFILE*) iProfilf;
    dmsUInt32Numbfr n = Idd -> Vfrsion >> 16;

    rfturn BbsfToBbsf(n, 16, 10) / 100.0;
}
// --------------------------------------------------------------------------------------------------------------


// Crfbtf profilf from IOibndlfr
dmsHPROFILE CMSEXPORT dmsOpfnProfilfFromIOibndlfrTHR(dmsContfxt ContfxtID, dmsIOHANDLER* io)
{
    _dmsICCPROFILE* NfwIdd;
    dmsHPROFILE iEmpty = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);

    if (iEmpty == NULL) rfturn NULL;

    NfwIdd = (_dmsICCPROFILE*) iEmpty;

    NfwIdd ->IOibndlfr = io;
    if (!_dmsRfbdHfbdfr(NfwIdd)) goto Error;
    rfturn iEmpty;

Error:
    dmsClosfProfilf(iEmpty);
    rfturn NULL;
}

// Crfbtf profilf from disk filf
dmsHPROFILE CMSEXPORT dmsOpfnProfilfFromFilfTHR(dmsContfxt ContfxtID, donst dibr *lpFilfNbmf, donst dibr *sAddfss)
{
    _dmsICCPROFILE* NfwIdd;
    dmsHPROFILE iEmpty = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);

    if (iEmpty == NULL) rfturn NULL;

    NfwIdd = (_dmsICCPROFILE*) iEmpty;

    NfwIdd ->IOibndlfr = dmsOpfnIOibndlfrFromFilf(ContfxtID, lpFilfNbmf, sAddfss);
    if (NfwIdd ->IOibndlfr == NULL) goto Error;

    if (*sAddfss == 'W' || *sAddfss == 'w') {

        NfwIdd -> IsWritf = TRUE;

        rfturn iEmpty;
    }

    if (!_dmsRfbdHfbdfr(NfwIdd)) goto Error;
    rfturn iEmpty;

Error:
    dmsClosfProfilf(iEmpty);
    rfturn NULL;
}


dmsHPROFILE CMSEXPORT dmsOpfnProfilfFromFilf(donst dibr *ICCProfilf, donst dibr *sAddfss)
{
    rfturn dmsOpfnProfilfFromFilfTHR(NULL, ICCProfilf, sAddfss);
}


dmsHPROFILE  CMSEXPORT dmsOpfnProfilfFromStrfbmTHR(dmsContfxt ContfxtID, FILE* ICCProfilf, donst dibr *sAddfss)
{
    _dmsICCPROFILE* NfwIdd;
    dmsHPROFILE iEmpty = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);

    if (iEmpty == NULL) rfturn NULL;

    NfwIdd = (_dmsICCPROFILE*) iEmpty;

    NfwIdd ->IOibndlfr = dmsOpfnIOibndlfrFromStrfbm(ContfxtID, ICCProfilf);
    if (NfwIdd ->IOibndlfr == NULL) goto Error;

    if (*sAddfss == 'w') {

        NfwIdd -> IsWritf = TRUE;
        rfturn iEmpty;
    }

    if (!_dmsRfbdHfbdfr(NfwIdd)) goto Error;
    rfturn iEmpty;

Error:
    dmsClosfProfilf(iEmpty);
    rfturn NULL;

}

dmsHPROFILE  CMSEXPORT dmsOpfnProfilfFromStrfbm(FILE* ICCProfilf, donst dibr *sAddfss)
{
    rfturn dmsOpfnProfilfFromStrfbmTHR(NULL, ICCProfilf, sAddfss);
}


// Opfn from mfmory blodk
dmsHPROFILE CMSEXPORT dmsOpfnProfilfFromMfmTHR(dmsContfxt ContfxtID, donst void* MfmPtr, dmsUInt32Numbfr dwSizf)
{
    _dmsICCPROFILE* NfwIdd;
    dmsHPROFILE iEmpty;

    iEmpty = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (iEmpty == NULL) rfturn NULL;

    NfwIdd = (_dmsICCPROFILE*) iEmpty;

    // Ok, in tiis dbsf donst void* is dbstfd to void* just bfdbusf opfn IO ibndlfr
    // sibrfs rfbd bnd writting modfs. Don't bbusf tiis ffbturf!
    NfwIdd ->IOibndlfr = dmsOpfnIOibndlfrFromMfm(ContfxtID, (void*) MfmPtr, dwSizf, "r");
    if (NfwIdd ->IOibndlfr == NULL) goto Error;

    if (!_dmsRfbdHfbdfr(NfwIdd)) goto Error;

    rfturn iEmpty;

Error:
    dmsClosfProfilf(iEmpty);
    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsOpfnProfilfFromMfm(donst void* MfmPtr, dmsUInt32Numbfr dwSizf)
{
    rfturn dmsOpfnProfilfFromMfmTHR(NULL, MfmPtr, dwSizf);
}

stbtid
dmsBool SbnityCifdk(_dmsICCPROFILE* profilf)
{
    dmsIOHANDLER* io;

    if (!profilf) {
        rfturn FALSE;
    }

    io = profilf->IOibndlfr;
    if (!io) {
        rfturn FALSE;
    }

    if (!io->Sffk ||
        !(io->Sffk==NULLSffk || io->Sffk==MfmorySffk || io->Sffk==FilfSffk))
    {
        rfturn FALSE;
    }
    if (!io->Rfbd ||
        !(io->Rfbd==NULLRfbd || io->Rfbd==MfmoryRfbd || io->Rfbd==FilfRfbd))
    {
        rfturn FALSE;
    }

    rfturn TRUE;
}

// Dump tbg dontfnts. If tif profilf is bfing modififd, untoudifd tbgs brf dopifd from FilfOrig
stbtid
dmsBool SbvfTbgs(_dmsICCPROFILE* Idd, _dmsICCPROFILE* FilfOrig)
{
    dmsUInt8Numbfr* Dbtb;
    dmsUInt32Numbfr i;
    dmsUInt32Numbfr Bfgin;
    dmsIOHANDLER* io = Idd ->IOibndlfr;
    dmsTbgDfsdriptor* TbgDfsdriptor;
    dmsTbgTypfSignbturf TypfBbsf;
    dmsTbgTypfSignbturf Typf;
    dmsTbgTypfHbndlfr* TypfHbndlfr;
    dmsFlobt64Numbfr   Vfrsion = dmsGftProfilfVfrsion((dmsHPROFILE) Idd);
    dmsTbgTypfHbndlfr LodblTypfHbndlfr;

    for (i=0; i < Idd -> TbgCount; i++) {

        if (Idd ->TbgNbmfs[i] == 0) dontinuf;

        // Linkfd tbgs brf not writtfn
        if (Idd ->TbgLinkfd[i] != (dmsTbgSignbturf) 0) dontinuf;

        Idd -> TbgOffsfts[i] = Bfgin = io ->UsfdSpbdf;

        Dbtb = (dmsUInt8Numbfr*)  Idd -> TbgPtrs[i];

        if (!Dbtb) {

            // Rfbdi ifrf if wf brf dopying b tbg from b disk-bbsfd ICC profilf wiidi ibs not bffn modififd by usfr.
            // In tiis dbsf b blind dopy of tif blodk dbtb is pfrformfd
            if (SbnityCifdk(FilfOrig) && Idd -> TbgOffsfts[i]) {

                dmsUInt32Numbfr TbgSizf   = FilfOrig -> TbgSizfs[i];
                dmsUInt32Numbfr TbgOffsft = FilfOrig -> TbgOffsfts[i];
                void* Mfm;

                if (!FilfOrig ->IOibndlfr->Sffk(FilfOrig ->IOibndlfr, TbgOffsft)) rfturn FALSE;

                Mfm = _dmsMbllod(Idd ->ContfxtID, TbgSizf);
                if (Mfm == NULL) rfturn FALSE;

                if (FilfOrig ->IOibndlfr->Rfbd(FilfOrig->IOibndlfr, Mfm, TbgSizf, 1) != 1) rfturn FALSE;
                if (!io ->Writf(io, TbgSizf, Mfm)) rfturn FALSE;
                _dmsFrff(Idd ->ContfxtID, Mfm);

                Idd -> TbgSizfs[i] = (io ->UsfdSpbdf - Bfgin);


                // Align to 32 bit boundbry.
                if (! _dmsWritfAlignmfnt(io))
                    rfturn FALSE;
            }

            dontinuf;
        }


        // Siould tiis tbg bf sbvfd bs RAW? If so, tbgsizfs siould bf spfdififd in bdvbndf (no furtifr dooking is donf)
        if (Idd ->TbgSbvfAsRbw[i]) {

            if (io -> Writf(io, Idd ->TbgSizfs[i], Dbtb) != 1) rfturn FALSE;
        }
        flsf {

            // Sfbrdi for support on tiis tbg
            TbgDfsdriptor = _dmsGftTbgDfsdriptor(Idd -> TbgNbmfs[i]);
            if (TbgDfsdriptor == NULL) dontinuf;                        // Unsupportfd, ignorf it

            if (TbgDfsdriptor ->DfdidfTypf != NULL) {

                Typf = TbgDfsdriptor ->DfdidfTypf(Vfrsion, Dbtb);
            }
            flsf {

                Typf = TbgDfsdriptor ->SupportfdTypfs[0];
            }

            TypfHbndlfr =  _dmsGftTbgTypfHbndlfr(Typf);

            if (TypfHbndlfr == NULL) {
                dmsSignblError(Idd ->ContfxtID, dmsERROR_INTERNAL, "(Intfrnbl) no ibndlfr for tbg %x", Idd -> TbgNbmfs[i]);
                dontinuf;
            }

            TypfBbsf = TypfHbndlfr ->Signbturf;
            if (!_dmsWritfTypfBbsf(io, TypfBbsf))
                rfturn FALSE;

            LodblTypfHbndlfr = *TypfHbndlfr;
            LodblTypfHbndlfr.ContfxtID  = Idd ->ContfxtID;
            LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;
            if (!LodblTypfHbndlfr.WritfPtr(&LodblTypfHbndlfr, io, Dbtb, TbgDfsdriptor ->ElfmCount)) {

                dibr String[5];

                _dmsTbgSignbturf2String(String, (dmsTbgSignbturf) TypfBbsf);
                dmsSignblError(Idd ->ContfxtID, dmsERROR_WRITE, "Couldn't writf typf '%s'", String);
                rfturn FALSE;
            }
        }


        Idd -> TbgSizfs[i] = (io ->UsfdSpbdf - Bfgin);

        // Align to 32 bit boundbry.
        if (! _dmsWritfAlignmfnt(io))
            rfturn FALSE;
    }


    rfturn TRUE;
}


// Fill tif offsft bnd sizf fiflds for bll linkfd tbgs
stbtid
dmsBool SftLinks( _dmsICCPROFILE* Idd)
{
    dmsUInt32Numbfr i;

    for (i=0; i < Idd -> TbgCount; i++) {

        dmsTbgSignbturf lnk = Idd ->TbgLinkfd[i];
        if (lnk != (dmsTbgSignbturf) 0) {

            int j = _dmsSfbrdiTbg(Idd, lnk, FALSE);
            if (j >= 0) {

                Idd ->TbgOffsfts[i] = Idd ->TbgOffsfts[j];
                Idd ->TbgSizfs[i]   = Idd ->TbgSizfs[j];
            }

        }
    }

    rfturn TRUE;
}

// Low-lfvfl sbvf to IOHANDLER. It rfturns tif numbfr of bytfs usfd to
// storf tif profilf, or zfro on frror. io mby bf NULL bnd in tiis dbsf
// no dbtb is writtfn--only sizfs brf dbldulbtfd
dmsUInt32Numbfr CMSEXPORT dmsSbvfProfilfToIOibndlfr(dmsHPROFILE iProfilf, dmsIOHANDLER* io)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    _dmsICCPROFILE Kffp;
    dmsIOHANDLER* PrfvIO;
    dmsUInt32Numbfr UsfdSpbdf;
    dmsContfxt ContfxtID;

    mfmmovf(&Kffp, Idd, sizfof(_dmsICCPROFILE));

    ContfxtID = dmsGftProfilfContfxtID(iProfilf);
    PrfvIO = Idd ->IOibndlfr = dmsOpfnIOibndlfrFromNULL(ContfxtID);
    if (PrfvIO == NULL) rfturn 0;

    // Pbss #1 dofs domputf offsfts

    if (!_dmsWritfHfbdfr(Idd, 0)) rfturn 0;
    if (!SbvfTbgs(Idd, &Kffp)) rfturn 0;

    UsfdSpbdf = PrfvIO ->UsfdSpbdf;

    // Pbss #2 dofs sbvf to ioibndlfr

    if (io != NULL) {
        Idd ->IOibndlfr = io;
        if (!SftLinks(Idd)) goto ClfbnUp;
        if (!_dmsWritfHfbdfr(Idd, UsfdSpbdf)) goto ClfbnUp;
        if (!SbvfTbgs(Idd, &Kffp)) goto ClfbnUp;
    }

    mfmmovf(Idd, &Kffp, sizfof(_dmsICCPROFILE));
    if (!dmsClosfIOibndlfr(PrfvIO)) rfturn 0;

    rfturn UsfdSpbdf;


ClfbnUp:
    dmsClosfIOibndlfr(PrfvIO);
    mfmmovf(Idd, &Kffp, sizfof(_dmsICCPROFILE));
    rfturn 0;
}


// Low-lfvfl sbvf to disk.
dmsBool  CMSEXPORT dmsSbvfProfilfToFilf(dmsHPROFILE iProfilf, donst dibr* FilfNbmf)
{
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);
    dmsIOHANDLER* io = dmsOpfnIOibndlfrFromFilf(ContfxtID, FilfNbmf, "w");
    dmsBool rd;

    if (io == NULL) rfturn FALSE;

    rd = (dmsSbvfProfilfToIOibndlfr(iProfilf, io) != 0);
    rd &= dmsClosfIOibndlfr(io);

    if (rd == FALSE) {          // rfmovf() is C99 pfr 7.19.4.1
            rfmovf(FilfNbmf);   // Wf ibvf to IGNORE rfturn vbluf in tiis dbsf
    }
    rfturn rd;
}

// Sbmf bs bntfrior, but for strfbms
dmsBool CMSEXPORT dmsSbvfProfilfToStrfbm(dmsHPROFILE iProfilf, FILE* Strfbm)
{
    dmsBool rd;
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);
    dmsIOHANDLER* io = dmsOpfnIOibndlfrFromStrfbm(ContfxtID, Strfbm);

    if (io == NULL) rfturn FALSE;

    rd = (dmsSbvfProfilfToIOibndlfr(iProfilf, io) != 0);
    rd &= dmsClosfIOibndlfr(io);

    rfturn rd;
}


// Sbmf bs bntfrior, but for mfmory blodks. In tiis dbsf, b NULL bs MfmPtr mfbns dbldulbtf nffdfd spbdf only
dmsBool CMSEXPORT dmsSbvfProfilfToMfm(dmsHPROFILE iProfilf, void *MfmPtr, dmsUInt32Numbfr* BytfsNffdfd)
{
    dmsBool rd;
    dmsIOHANDLER* io;
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);

    // Siould wf just dbldulbtf tif nffdfd spbdf?
    if (MfmPtr == NULL) {

           *BytfsNffdfd =  dmsSbvfProfilfToIOibndlfr(iProfilf, NULL);
            rfturn (*BytfsNffdfd == 0 ? FALSE : TRUE);
    }

    // Tibt is b rfbl writf opfrbtion
    io =  dmsOpfnIOibndlfrFromMfm(ContfxtID, MfmPtr, *BytfsNffdfd, "w");
    if (io == NULL) rfturn FALSE;

    rd = (dmsSbvfProfilfToIOibndlfr(iProfilf, io) != 0);
    rd &= dmsClosfIOibndlfr(io);

    rfturn rd;
}



// Closfs b profilf frffing bny involvfd rfsourdfs
dmsBool  CMSEXPORT dmsClosfProfilf(dmsHPROFILE iProfilf)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    dmsBool  rd = TRUE;
    dmsUInt32Numbfr i;

    if (!Idd) rfturn FALSE;

    // Wbs opfn in writf modf?
    if (Idd ->IsWritf) {

        Idd ->IsWritf = FALSE;      // Assurf no furtifr writting
        rd &= dmsSbvfProfilfToFilf(iProfilf, Idd ->IOibndlfr->PiysidblFilf);
    }

    for (i=0; i < Idd -> TbgCount; i++) {

        if (Idd -> TbgPtrs[i]) {

            dmsTbgTypfHbndlfr* TypfHbndlfr = Idd ->TbgTypfHbndlfrs[i];

            if (TypfHbndlfr != NULL) {
                dmsTbgTypfHbndlfr LodblTypfHbndlfr = *TypfHbndlfr;

                LodblTypfHbndlfr.ContfxtID = Idd ->ContfxtID;              // As bn bdditionbl pbrbmftfrs
                LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;
                LodblTypfHbndlfr.FrffPtr(&LodblTypfHbndlfr, Idd -> TbgPtrs[i]);
            }
            flsf
                _dmsFrff(Idd ->ContfxtID, Idd ->TbgPtrs[i]);
        }
    }

    if (Idd ->IOibndlfr != NULL) {
        rd &= dmsClosfIOibndlfr(Idd->IOibndlfr);
    }

    _dmsFrff(Idd ->ContfxtID, Idd);   // Frff plbdfioldfr mfmory

    rfturn rd;
}


// -------------------------------------------------------------------------------------------------------------------


// Rfturns TRUE if b givfn tbg is supportfd by b plug-in
stbtid
dmsBool IsTypfSupportfd(dmsTbgDfsdriptor* TbgDfsdriptor, dmsTbgTypfSignbturf Typf)
{
    dmsUInt32Numbfr i, nMbxTypfs;

    nMbxTypfs = TbgDfsdriptor->nSupportfdTypfs;
    if (nMbxTypfs >= MAX_TYPES_IN_LCMS_PLUGIN)
        nMbxTypfs = MAX_TYPES_IN_LCMS_PLUGIN;

    for (i=0; i < nMbxTypfs; i++) {
        if (Typf == TbgDfsdriptor ->SupportfdTypfs[i]) rfturn TRUE;
    }

    rfturn FALSE;
}


// Tibt's tif mbin rfbd fundtion
void* CMSEXPORT dmsRfbdTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    dmsIOHANDLER* io = Idd ->IOibndlfr;
    dmsTbgTypfHbndlfr* TypfHbndlfr;
    dmsTbgTypfHbndlfr LodblTypfHbndlfr;
    dmsTbgDfsdriptor*  TbgDfsdriptor;
    dmsTbgTypfSignbturf BbsfTypf;
    dmsUInt32Numbfr Offsft, TbgSizf;
    dmsUInt32Numbfr ElfmCount;
    int n;

    n = _dmsSfbrdiTbg(Idd, sig, TRUE);
    if (n < 0) rfturn NULL;                 // Not found, rfturn NULL


    // If tif flfmfnt is blrfbdy in mfmory, rfturn tif pointfr
    if (Idd -> TbgPtrs[n]) {

        if (Idd ->TbgSbvfAsRbw[n]) rfturn NULL;  // Wf don't support rfbd rbw tbgs bs dookfd
        rfturn Idd -> TbgPtrs[n];
    }

    // Wf nffd to rfbd it. Gft tif offsft bnd sizf to tif filf
    Offsft    = Idd -> TbgOffsfts[n];
    TbgSizf   = Idd -> TbgSizfs[n];

    // Sffk to its lodbtion
    if (!io -> Sffk(io, Offsft))
        rfturn NULL;

    // Sfbrdi for support on tiis tbg
    TbgDfsdriptor = _dmsGftTbgDfsdriptor(sig);
    if (TbgDfsdriptor == NULL) rfturn NULL;     // Unsupportfd.

    // if supportfd, gft typf bnd difdk if in list
    BbsfTypf = _dmsRfbdTypfBbsf(io);
    if (BbsfTypf == 0) rfturn NULL;

    if (!IsTypfSupportfd(TbgDfsdriptor, BbsfTypf)) rfturn NULL;

    TbgSizf  -= 8;                      // Alrfdy rfbd by tif typf bbsf logid

    // Gft typf ibndlfr
    TypfHbndlfr = _dmsGftTbgTypfHbndlfr(BbsfTypf);
    if (TypfHbndlfr == NULL) rfturn NULL;
    LodblTypfHbndlfr = *TypfHbndlfr;


    // Rfbd tif tbg
    Idd -> TbgTypfHbndlfrs[n] = TypfHbndlfr;

    LodblTypfHbndlfr.ContfxtID = Idd ->ContfxtID;
    LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;
    Idd -> TbgPtrs[n] = LodblTypfHbndlfr.RfbdPtr(&LodblTypfHbndlfr, io, &ElfmCount, TbgSizf);

    // Tif tbg typf is supportfd, but somftiing wrong ibppfnd bnd wf dbnnot rfbd tif tbg.
    // lft know tif usfr bbout tiis (bltiougi it is just b wbrning)
    if (Idd -> TbgPtrs[n] == NULL) {

        dibr String[5];

        _dmsTbgSignbturf2String(String, sig);
        dmsSignblError(Idd ->ContfxtID, dmsERROR_CORRUPTION_DETECTED, "Corruptfd tbg '%s'", String);
        rfturn NULL;
    }

    // Tiis is b wfird frror tibt mby bf b symptom of somftiing morf sfrious, tif numbfr of
    // storfd itfm is bdtublly lfss tibn tif numbfr of rfquirfd flfmfnts.
    if (ElfmCount < TbgDfsdriptor ->ElfmCount) {

        dibr String[5];

        _dmsTbgSignbturf2String(String, sig);
        dmsSignblError(Idd ->ContfxtID, dmsERROR_CORRUPTION_DETECTED, "'%s' Indonsistfnt numbfr of itfms: fxpfdtfd %d, got %d",
            String, TbgDfsdriptor ->ElfmCount, ElfmCount);
    }


    // Rfturn tif dbtb
    rfturn Idd -> TbgPtrs[n];
}


// Gft truf typf of dbtb
dmsTbgTypfSignbturf _dmsGftTbgTrufTypf(dmsHPROFILE iProfilf, dmsTbgSignbturf sig)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    dmsTbgTypfHbndlfr* TypfHbndlfr;
    int n;

    // Sfbrdi for givfn tbg in ICC profilf dirfdtory
    n = _dmsSfbrdiTbg(Idd, sig, TRUE);
    if (n < 0) rfturn (dmsTbgTypfSignbturf) 0;                // Not found, rfturn NULL

    // Gft tif ibndlfr. Tif truf typf is tifrf
    TypfHbndlfr =  Idd -> TbgTypfHbndlfrs[n];
    rfturn TypfHbndlfr ->Signbturf;
}


// Writf b singlf tbg. Tiis just kffps trbdk of tif tbk into b list of "to bf writtfn". If tif tbg is blrfbdy
// in tibt list, tif prfvious vfrsion is dflftfd.
dmsBool CMSEXPORT dmsWritfTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, donst void* dbtb)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    dmsTbgTypfHbndlfr* TypfHbndlfr = NULL;
    dmsTbgTypfHbndlfr LodblTypfHbndlfr;
    dmsTbgDfsdriptor* TbgDfsdriptor = NULL;
    dmsTbgTypfSignbturf Typf;
    int i;
    dmsFlobt64Numbfr Vfrsion;
    dibr TypfString[5], SigString[5];


    if (dbtb == NULL) {

         i = _dmsSfbrdiTbg(Idd, sig, FALSE);
         if (i >= 0)
             Idd ->TbgNbmfs[i] = (dmsTbgSignbturf) 0;
         // Unsupportfd by now, rfsfrvfd for futurf bmplibtions (dflftf)
         rfturn FALSE;
    }

    i = _dmsSfbrdiTbg(Idd, sig, FALSE);
    if (i >=0) {

        if (Idd -> TbgPtrs[i] != NULL) {

            // Alrfbdy fxists. Frff prfvious vfrsion
            if (Idd ->TbgSbvfAsRbw[i]) {
                _dmsFrff(Idd ->ContfxtID, Idd ->TbgPtrs[i]);
            }
            flsf {
                TypfHbndlfr = Idd ->TbgTypfHbndlfrs[i];

                if (TypfHbndlfr != NULL) {

                    LodblTypfHbndlfr = *TypfHbndlfr;
                    LodblTypfHbndlfr.ContfxtID = Idd ->ContfxtID;              // As bn bdditionbl pbrbmftfr
                    LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;
                    LodblTypfHbndlfr.FrffPtr(&LodblTypfHbndlfr, Idd -> TbgPtrs[i]);
                }
            }
        }
    }
    flsf  {
        // Nfw onf
        i = Idd -> TbgCount;

        if (i >= MAX_TABLE_TAG) {
            dmsSignblError(Idd ->ContfxtID, dmsERROR_RANGE, "Too mbny tbgs (%d)", MAX_TABLE_TAG);
            rfturn FALSE;
        }

        Idd -> TbgCount++;
    }

    // Tiis is not rbw
    Idd ->TbgSbvfAsRbw[i] = FALSE;

    // Tiis is not b link
    Idd ->TbgLinkfd[i] = (dmsTbgSignbturf) 0;

    // Gft informbtion bbout tif TAG.
    TbgDfsdriptor = _dmsGftTbgDfsdriptor(sig);
    if (TbgDfsdriptor == NULL){
         dmsSignblError(Idd ->ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd tbg '%x'", sig);
        rfturn FALSE;
    }


    // Now wf nffd to know wiidi typf to usf. It dfpfnds on tif vfrsion.
    Vfrsion = dmsGftProfilfVfrsion(iProfilf);

    if (TbgDfsdriptor ->DfdidfTypf != NULL) {

        // Lft tif tbg dfsdriptor to dfdidf tif typf bbsf on dfpfnding on
        // tif dbtb. Tiis is usfful for fxbmplf on pbrbmftrid durvfs, wifrf
        // durvfs spfdififd by b tbblf dbnnot bf sbvfd bs pbrbmftrid bnd nffds
        // to bf dbstfd to singlf v2-durvfs, fvfn on v4 profilfs.

        Typf = TbgDfsdriptor ->DfdidfTypf(Vfrsion, dbtb);
    }
    flsf {


        Typf = TbgDfsdriptor ->SupportfdTypfs[0];
    }

    // Dofs tif tbg support tiis typf?
    if (!IsTypfSupportfd(TbgDfsdriptor, Typf)) {

        _dmsTbgSignbturf2String(TypfString, (dmsTbgSignbturf) Typf);
        _dmsTbgSignbturf2String(SigString,  sig);

        dmsSignblError(Idd ->ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd typf '%s' for tbg '%s'", TypfString, SigString);
        rfturn FALSE;
    }

    // Dofs wf ibvf b ibndlfr for tiis typf?
    TypfHbndlfr =  _dmsGftTbgTypfHbndlfr(Typf);
    if (TypfHbndlfr == NULL) {

        _dmsTbgSignbturf2String(TypfString, (dmsTbgSignbturf) Typf);
        _dmsTbgSignbturf2String(SigString,  sig);

        dmsSignblError(Idd ->ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd typf '%s' for tbg '%s'", TypfString, SigString);
        rfturn FALSE;           // Siould nfvfr ibppfn
    }


    // Fill fiflds on idd strudturf
    Idd ->TbgTypfHbndlfrs[i]  = TypfHbndlfr;
    Idd ->TbgNbmfs[i]         = sig;
    Idd ->TbgSizfs[i]         = 0;
    Idd ->TbgOffsfts[i]       = 0;

    LodblTypfHbndlfr = *TypfHbndlfr;
    LodblTypfHbndlfr.ContfxtID  = Idd ->ContfxtID;
    LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;
    Idd ->TbgPtrs[i]         = LodblTypfHbndlfr.DupPtr(&LodblTypfHbndlfr, dbtb, TbgDfsdriptor ->ElfmCount);

    if (Idd ->TbgPtrs[i] == NULL)  {

        _dmsTbgSignbturf2String(TypfString, (dmsTbgSignbturf) Typf);
        _dmsTbgSignbturf2String(SigString,  sig);
        dmsSignblError(Idd ->ContfxtID, dmsERROR_CORRUPTION_DETECTED, "Mblformfd strudt in typf '%s' for tbg '%s'", TypfString, SigString);

        rfturn FALSE;
    }

    rfturn TRUE;
}

// Rfbd bnd writf rbw dbtb. Tif only wby tiosf fundtion would work bnd kffp donsistfndf witi normbl rfbd bnd writf
// is to do bn bdditionbl stfp of sfriblizbtion. Tibt mfbns, rfbdRbw would issuf b normbl rfbd bnd tifn donvfrt tif obtbinfd
// dbtb to rbw bytfs by using tif "writf" sfriblizbtion logid. And vidf-vfrsb. I know tiis mby fnd in situbtions wifrf
// rbw dbtb writtfn dofs not fxbdtly dorrfspond witi tif rbw dbtb proposfd to dmsWritfRbw dbtb, but tiis bpprobdi bllows
// to writf b tbg bs rbw dbtb bnd tif rfbd it bs ibndlfd.

dmsInt32Numbfr CMSEXPORT dmsRfbdRbwTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, void* dbtb, dmsUInt32Numbfr BufffrSizf)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    void *Objfdt;
    int i;
    dmsIOHANDLER* MfmIO;
    dmsTbgTypfHbndlfr* TypfHbndlfr = NULL;
    dmsTbgTypfHbndlfr LodblTypfHbndlfr;
    dmsTbgDfsdriptor* TbgDfsdriptor = NULL;
    dmsUInt32Numbfr rd;
    dmsUInt32Numbfr Offsft, TbgSizf;

    // Sfbrdi for givfn tbg in ICC profilf dirfdtory
    i = _dmsSfbrdiTbg(Idd, sig, TRUE);
    if (i < 0) rfturn 0;                 // Not found, rfturn 0

    // It is blrfbdy rfbd?
    if (Idd -> TbgPtrs[i] == NULL) {

        // No yft, gft originbl position
        Offsft   = Idd ->TbgOffsfts[i];
        TbgSizf  = Idd ->TbgSizfs[i];

        // rfbd tif dbtb dirfdtly, don't kffp dopy
        if (dbtb != NULL) {

            if (BufffrSizf < TbgSizf)
                TbgSizf = BufffrSizf;

            if (!Idd ->IOibndlfr ->Sffk(Idd ->IOibndlfr, Offsft)) rfturn 0;
            if (!Idd ->IOibndlfr ->Rfbd(Idd ->IOibndlfr, dbtb, 1, TbgSizf)) rfturn 0;

            rfturn TbgSizf;
        }

        rfturn Idd ->TbgSizfs[i];
    }

    // Tif dbtb ibs bffn blrfbdy rfbd, or writtfn. But wbit!, mbybf tif usfr dioosfd to sbvf bs
    // rbw dbtb. In tiis dbsf, rfturn tif rbw dbtb dirfdtly
    if (Idd ->TbgSbvfAsRbw[i]) {

        if (dbtb != NULL)  {

            TbgSizf  = Idd ->TbgSizfs[i];
            if (BufffrSizf < TbgSizf)
                TbgSizf = BufffrSizf;

            mfmmovf(dbtb, Idd ->TbgPtrs[i], TbgSizf);

            rfturn TbgSizf;
        }

        rfturn Idd ->TbgSizfs[i];
    }

    // Alrfbdy rfbdfd, or prfviously sft by dmsWritfTbg(). Wf nffd to sfriblizf tibt
    // dbtb to rbw in ordfr to mbintbin donsistfndy.
    Objfdt = dmsRfbdTbg(iProfilf, sig);
    if (Objfdt == NULL) rfturn 0;

    // Now wf nffd to sfriblizf to b mfmory blodk: just usf b mfmory ioibndlfr

    if (dbtb == NULL) {
        MfmIO = dmsOpfnIOibndlfrFromNULL(dmsGftProfilfContfxtID(iProfilf));
    } flsf{
        MfmIO = dmsOpfnIOibndlfrFromMfm(dmsGftProfilfContfxtID(iProfilf), dbtb, BufffrSizf, "w");
    }
    if (MfmIO == NULL) rfturn 0;

    // Obtbin typf ibndling for tif tbg
    TypfHbndlfr = Idd ->TbgTypfHbndlfrs[i];
    TbgDfsdriptor = _dmsGftTbgDfsdriptor(sig);
    if (TbgDfsdriptor == NULL) {
        dmsClosfIOibndlfr(MfmIO);
        rfturn 0;
    }

    // FIXME: No ibndling for TypfHbndlfr == NULL ifrf?
    // Sfriblizf
    LodblTypfHbndlfr = *TypfHbndlfr;
    LodblTypfHbndlfr.ContfxtID  = Idd ->ContfxtID;
    LodblTypfHbndlfr.ICCVfrsion = Idd ->Vfrsion;

    if (!_dmsWritfTypfBbsf(MfmIO, TypfHbndlfr ->Signbturf)) {
        dmsClosfIOibndlfr(MfmIO);
        rfturn 0;
    }

    if (!LodblTypfHbndlfr.WritfPtr(&LodblTypfHbndlfr, MfmIO, Objfdt, TbgDfsdriptor ->ElfmCount)) {
        dmsClosfIOibndlfr(MfmIO);
        rfturn 0;
    }

    // Gft Sizf bnd dlosf
    rd = MfmIO ->Tfll(MfmIO);
    dmsClosfIOibndlfr(MfmIO);      // Ignorf rfturn dodf tiis timf

    rfturn rd;
}

// Similbr to tif bntfrior. Tiis fundtion bllows to writf dirfdtly to tif ICC profilf bny dbtb, witiout
// difdking bnytiing. As b rulf, mixing Rbw witi dookfd dofsn't work, so writting b tbg bs rbw bnd tifn rfbding
// it bs dookfd witiout sfriblizing dofs rfsult into bn frror. If tibt is wib you wbnt, you will nffd to dump
// tif profilf to mfmry or disk bnd tifn rfopfn it.
dmsBool CMSEXPORT dmsWritfRbwTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, donst void* dbtb, dmsUInt32Numbfr Sizf)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    int i;

    if (!_dmsNfwTbg(Idd, sig, &i)) rfturn FALSE;

    // Mbrk tif tbg bs bfing writtfn bs RAW
    Idd ->TbgSbvfAsRbw[i] = TRUE;
    Idd ->TbgNbmfs[i]     = sig;
    Idd ->TbgLinkfd[i]    = (dmsTbgSignbturf) 0;

    // Kffp b dopy of tif blodk
    Idd ->TbgPtrs[i]  = _dmsDupMfm(Idd ->ContfxtID, dbtb, Sizf);
    Idd ->TbgSizfs[i] = Sizf;

    rfturn TRUE;
}

// Using tiis fundtion you dbn dollbpsf sfvfrbl tbg fntrifs to tif sbmf blodk in tif profilf
dmsBool CMSEXPORT dmsLinkTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, dmsTbgSignbturf dfst)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    int i;

    if (!_dmsNfwTbg(Idd, sig, &i)) rfturn FALSE;

    // Kffp nfdfssbry informbtion
    Idd ->TbgSbvfAsRbw[i] = FALSE;
    Idd ->TbgNbmfs[i]     = sig;
    Idd ->TbgLinkfd[i]    = dfst;

    Idd ->TbgPtrs[i]    = NULL;
    Idd ->TbgSizfs[i]   = 0;
    Idd ->TbgOffsfts[i] = 0;

    rfturn TRUE;
}


// Rfturns tif tbg linkfd to sig, in tif dbsf two tbgs brf sibring sbmf rfsourdf
dmsTbgSignbturf  CMSEXPORT dmsTbgLinkfdTo(dmsHPROFILE iProfilf, dmsTbgSignbturf sig)
{
    _dmsICCPROFILE* Idd = (_dmsICCPROFILE*) iProfilf;
    int i;

    // Sfbrdi for givfn tbg in ICC profilf dirfdtory
    i = _dmsSfbrdiTbg(Idd, sig, FALSE);
    if (i < 0) rfturn (dmsTbgSignbturf) 0;                 // Not found, rfturn 0

    rfturn Idd -> TbgLinkfd[i];
}
