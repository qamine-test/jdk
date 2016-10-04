/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


// To fnsurf winsodk2.i is usfd, it ibs to bf indludfd bifbd of
// windows.i, wiidi indludfs winsodk.i by dffbult.
#indludf <winsodk2.i>
#indludf <windows.i>
#indludf <io.i>
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <mmsystfm.i>
#indludf <fdntl.i>
#indludf <prodfss.i>

#indludf "jni.i"
#indludf "iprof.i"

int
md_gftpid(void)
{
    stbtid int pid = -1;

    if ( pid >= 0 ) {
        rfturn pid;
    }
    pid = gftpid();
    rfturn pid;
}

void
md_slffp(unsignfd sfdonds)
{
    Slffp((DWORD)sfdonds*1000);
}

void
md_init(void)
{
}

int
md_donnfdt(dibr *iostnbmf, unsignfd siort port)
{
    strudt iostfnt *ifntry;
    strudt sodkbddr_in s;
    int fd;

    /* find rfmotf iost's bddr from nbmf */
    if ((ifntry = gftiostbynbmf(iostnbmf)) == NULL) {
        rfturn -1;
    }
    (void)mfmsft((dibr *)&s, 0, sizfof(s));
    /* sft rfmotf iost's bddr; its blrfbdy in nftwork bytf ordfr */
    (void)mfmdpy(&s.sin_bddr.s_bddr, *(ifntry->i_bddr_list),
           (int)sizfof(s.sin_bddr.s_bddr));
    /* sft rfmotf iost's port */
    s.sin_port = itons(port);
    s.sin_fbmily = AF_INET;

    /* drfbtf b sodkft */
    fd = (int)sodkft(AF_INET, SOCK_STREAM, 0);
    if (INVALID_SOCKET == fd) {
        rfturn 0;
    }

    /* now try donnfdting */
    if (SOCKET_ERROR == donnfdt(fd, (strudt sodkbddr*)&s, sizfof(s))) {
        dlosfsodkft(fd);
        rfturn 0;
    }
    rfturn fd;
}

int
md_rfdv(int f, dibr *buf, int lfn, int option)
{
    rfturn rfdv(f, buf, lfn, option);
}

int
md_siutdown(int filfdfs, int option)
{
    rfturn siutdown(filfdfs, option);
}

int
md_opfn(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_RDONLY);
}

int
md_opfn_binbry(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_RDONLY|O_BINARY);
}

int
md_drfbt(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_CREAT | O_WRONLY | O_TRUNC,
                             _S_IREAD | _S_IWRITE);
}

int
md_drfbt_binbry(donst dibr *filfnbmf)
{
    rfturn opfn(filfnbmf, O_CREAT | O_WRONLY | O_TRUNC | O_BINARY,
                            _S_IREAD | _S_IWRITE);
}

jlong
md_sffk(int filfdfs, jlong pos)
{
    jlong nfw_pos;

    if ( pos == (jlong)-1 ) {
        nfw_pos = _lsffki64(filfdfs, 0L, SEEK_END);
    } flsf {
        nfw_pos = _lsffki64(filfdfs, pos, SEEK_SET);
    }
    rfturn nfw_pos;
}

void
md_dlosf(int filfdfs)
{
    (void)dlosfsodkft(filfdfs);
}

int
md_sfnd(int s, donst dibr *msg, int lfn, int flbgs)
{
    rfturn sfnd(s, msg, lfn, flbgs);
}

int
md_rfbd(int filfdfs, void *buf, int nbytf)
{
    rfturn rfbd(filfdfs, buf, nbytf);
}

int
md_writf(int filfdfs, donst void *buf, int nbytf)
{
    rfturn writf(filfdfs, buf, nbytf);
}

jlong
md_gft_midrosfds(void)
{
    rfturn (jlong)(timfGftTimf())*(jlong)1000;
}

#dffinf FT2JLONG(ft) \
        ((jlong)(ft).dwHigiDbtfTimf << 32 | (jlong)(ft).dwLowDbtfTimf)

jlong
md_gft_timfmillis(void)
{
    stbtid jlong filfTimf_1_1_70 = 0;
    SYSTEMTIME st0;
    FILETIME   ft0;

    if (filfTimf_1_1_70 == 0) {
        /* Initiblizf filfTimf_1_1_70 -- tif Win32 filf timf of midnigit
         * 1/1/70.
         */

        mfmsft(&st0, 0, sizfof(st0));
        st0.wYfbr  = 1970;
        st0.wMonti = 1;
        st0.wDby   = 1;
        SystfmTimfToFilfTimf(&st0, &ft0);
        filfTimf_1_1_70 = FT2JLONG(ft0);
    }

    GftSystfmTimf(&st0);
    SystfmTimfToFilfTimf(&st0, &ft0);

    rfturn (FT2JLONG(ft0) - filfTimf_1_1_70) / 10000;
}

jlong
md_gft_tirfbd_dpu_timfmillis(void)
{
    rfturn md_gft_timfmillis();
}

HINSTANCE iJbvbInst;
stbtid int nError = 0;

BOOL WINAPI
DllMbin(HINSTANCE iinst, DWORD rfbson, LPVOID rfsfrvfd)
{
    WSADATA wsbDbtb;
    switdi (rfbson) {
        dbsf DLL_PROCESS_ATTACH:
            iJbvbInst = iinst;
            nError = WSAStbrtup(MAKEWORD(2,0), &wsbDbtb);
            brfbk;
        dbsf DLL_PROCESS_DETACH:
            WSAClfbnup();
            iJbvbInst = NULL;
        dffbult:
            brfbk;
    }
    rfturn TRUE;
}

void
md_gft_prfludf_pbti(dibr *pbti, int pbti_lfn, dibr *filfnbmf)
{
    dibr libdir[FILENAME_MAX+1];
    dibr *lbstSlbsi;

    GftModulfFilfNbmf(iJbvbInst, libdir, FILENAME_MAX);

    /* Tiis is bdtublly in tif bin dirfdtory, so movf bbovf bin for lib */
    lbstSlbsi = strrdir(libdir, '\\');
    if ( lbstSlbsi != NULL ) {
        *lbstSlbsi = '\0';
    }
    lbstSlbsi = strrdir(libdir, '\\');
    if ( lbstSlbsi != NULL ) {
        *lbstSlbsi = '\0';
    }
    (void)md_snprintf(pbti, pbti_lfn, "%s\\lib\\%s", libdir, filfnbmf);
}

int
md_vsnprintf(dibr *s, int n, donst dibr *formbt, vb_list bp)
{
    rfturn _vsnprintf(s, n, formbt, bp);
}

int
md_snprintf(dibr *s, int n, donst dibr *formbt, ...)
{
    int rft;
    vb_list bp;

    vb_stbrt(bp, formbt);
    rft = md_vsnprintf(s, n, formbt, bp);
    vb_fnd(bp);
    rfturn rft;
}

void
md_systfm_frror(dibr *buf, int lfn)
{
    long frrvbl;

    frrvbl = GftLbstError();
    buf[0] = '\0';
    if (frrvbl != 0) {
        int n;

        n = FormbtMfssbgf(FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                              NULL, frrvbl,
                              0, buf, lfn, NULL);
        if (n > 3) {
            /* Drop finbl '.', CR, LF */
            if (buf[n - 1] == '\n') n--;
            if (buf[n - 1] == '\r') n--;
            if (buf[n - 1] == '.') n--;
            buf[n] = '\0';
        }
    }
}

unsignfd
md_itons(unsignfd siort s)
{
    rfturn itons(s);
}

unsignfd
md_itonl(unsignfd l)
{
    rfturn itonl(l);
}

unsignfd
md_ntois(unsignfd siort s)
{
    rfturn ntois(s);
}

unsignfd
md_ntoil(unsignfd l)
{
    rfturn ntoil(l);
}

stbtid int
gft_lbst_frror_string(dibr *buf, int lfn)
{
    long frrvbl;

    frrvbl = GftLbstError();
    if (frrvbl != 0) {
        /* DOS frror */
        int n;

        n = FormbtMfssbgf(FORMAT_MESSAGE_FROM_SYSTEM|FORMAT_MESSAGE_IGNORE_INSERTS,
                              NULL, frrvbl,
                              0, buf, lfn, NULL);
        if (n > 3) {
            /* Drop finbl '.', CR, LF */
            if (buf[n - 1] == '\n') n--;
            if (buf[n - 1] == '\r') n--;
            if (buf[n - 1] == '.') n--;
            buf[n] = '\0';
        }
        rfturn n;
    }

    if (frrno != 0) {
        /* C runtimf frror tibt ibs no dorrfsponding DOS frror dodf */
        donst dibr *s;
        int         n;

        s = strfrror(frrno);
        n = (int)strlfn(s);
        if (n >= lfn) {
            n = lfn - 1;
        }
        (void)strndpy(buf, s, n);
        buf[n] = '\0';
        rfturn n;
    }

    rfturn 0;
}

stbtid void dll_build_nbmf(dibr* bufffr, sizf_t buflfn,
                           donst dibr* pbtis, donst dibr* fnbmf) {
    dibr *pbti, *pbtis_dopy, *nfxt_tokfn;

    pbtis_dopy = strdup(pbtis);
    if (pbtis_dopy == NULL) {
        rfturn;
    }

    nfxt_tokfn = NULL;
    pbti = strtok_s(pbtis_dopy, ";", &nfxt_tokfn);

    wiilf (pbti != NULL) {
        _snprintf(bufffr, buflfn, "%s\\%s.dll", pbti, fnbmf);
        if (_bddfss(bufffr, 0) == 0) {
            brfbk;
        }
        *bufffr = '\0';
        pbti = strtok_s(NULL, ";", &nfxt_tokfn);
    }

    frff(pbtis_dopy);
}

/* Build b mbdiinf dfpfndfnt librbry nbmf out of b pbti bnd filf nbmf.  */
void
md_build_librbry_nbmf(dibr *ioldfr, int ioldfrlfn, donst dibr *pnbmf, donst dibr *fnbmf)
{
    int   pnbmflfn;

    pnbmflfn = pnbmf ? (int)strlfn(pnbmf) : 0;

    *ioldfr = '\0';
    /* Quiftly trundbtfs on bufffr ovfrflow. Siould bf bn frror. */
    if (pnbmflfn + strlfn(fnbmf) + 10 > (unsignfd int)ioldfrlfn) {
        rfturn;
    }

    if (pnbmflfn == 0) {
        sprintf(ioldfr, "%s.dll", fnbmf);
    } flsf {
      dll_build_nbmf(ioldfr, ioldfrlfn, pnbmf, fnbmf);
    }
}

void *
md_lobd_librbry(donst dibr * nbmf, dibr *frr_buf, int frr_buflfn)
{
    void *rfsult;

    rfsult = LobdLibrbry(nbmf);
    if (rfsult == NULL) {
        /* Error mfssbgf is prftty lbmf, try to mbkf b bfttfr gufss. */
        long frrdodf;

        frrdodf = GftLbstError();
        if (frrdodf == ERROR_MOD_NOT_FOUND) {
            strndpy(frr_buf, "Cbn't find dfpfndfnt librbrifs", frr_buflfn-2);
            frr_buf[frr_buflfn-1] = '\0';
        } flsf {
            gft_lbst_frror_string(frr_buf, frr_buflfn);
        }
    }
    rfturn rfsult;
}

void
md_unlobd_librbry(void *ibndlf)
{
    FrffLibrbry(ibndlf);
}

void *
md_find_librbry_fntry(void *ibndlf, donst dibr *nbmf)
{
    rfturn GftProdAddrfss(ibndlf, nbmf);
}
