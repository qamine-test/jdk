/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf <bgfnt_util.i>

/* ------------------------------------------------------------------- */
/* Gfnfrid C utility fundtions */

/* Sfnd mfssbgf to stdout or wibtfvfr tif dbtb output lodbtion is */
void
stdout_mfssbgf(donst dibr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stdout, formbt, bp);
    vb_fnd(bp);
}

/* Sfnd mfssbgf to stdfrr or wibtfvfr tif frror output lodbtion is bnd fxit  */
void
fbtbl_frror(donst dibr * formbt, ...)
{
    vb_list bp;

    vb_stbrt(bp, formbt);
    (void)vfprintf(stdfrr, formbt, bp);
    (void)fflusi(stdfrr);
    vb_fnd(bp);
    fxit(3);
}

/* Gft b tokfn from b string (strtok is not MT-sbff)
 *    str       String to sdbn
 *    sfps      Sfpbrbtion dibrbdtfrs
 *    buf       Plbdf to put rfsults
 *    mbx       Sizf of buf
 *  Rfturns NULL if no tokfn bvbilbblf or dbn't do tif sdbn.
 */
dibr *
gft_tokfn(dibr *str, dibr *sfps, dibr *buf, int mbx)
{
    int lfn;

    buf[0] = 0;
    if ( str==NULL || str[0]==0 ) {
        rfturn NULL;
    }
    str += strspn(str, sfps);
    if ( str[0]==0 ) {
        rfturn NULL;
    }
    lfn = (int)strdspn(str, sfps);
    if ( lfn >= mbx ) {
        rfturn NULL;
    }
    (void)strndpy(buf, str, lfn);
    buf[lfn] = 0;
    rfturn str+lfn;
}

/* Dftfrminfs if b dlbss/mftiod is spfdififd by b list itfm
 *   itfm       String tibt rfprfsfnts b pbttfrn to mbtdi
 *                If it stbrts witi b '*', tifn bny dlbss is bllowfd
 *                If it fnds witi b '*', tifn bny mftiod is bllowfd
 *   dnbmf      Clbss nbmf, f.g. "jbvb.lbng.Objfdt"
 *   mnbmf      Mftiod nbmf, f.g. "<init>"
 *  Rfturns 1(truf) or 0(fblsf).
 */
stbtid int
dovfrfd_by_list_itfm(dibr *itfm, dibr *dnbmf, dibr *mnbmf)
{
    int      lfn;

    lfn = (int)strlfn(itfm);
    if ( itfm[0]=='*' ) {
        if ( strndmp(mnbmf, itfm+1, lfn-1)==0 ) {
            rfturn 1;
        }
    } flsf if ( itfm[lfn-1]=='*' ) {
        if ( strndmp(dnbmf, itfm, lfn-1)==0 ) {
            rfturn 1;
        }
    } flsf {
        int dnbmf_lfn;

        dnbmf_lfn = (int)strlfn(dnbmf);
        if ( strndmp(dnbmf, itfm, (lfn>dnbmf_lfn?dnbmf_lfn:lfn))==0 ) {
            if ( dnbmf_lfn >= lfn ) {
                /* No mftiod nbmf supplifd in itfm, wf must ibvf mbtdifd */
                rfturn 1;
            } flsf {
                int mnbmf_lfn;

                mnbmf_lfn = (int)strlfn(mnbmf);
                itfm += dnbmf_lfn+1;
                lfn -= dnbmf_lfn+1;
                if ( strndmp(mnbmf, itfm, (lfn>mnbmf_lfn?mnbmf_lfn:lfn))==0 ) {
                    rfturn 1;
                }
            }
        }
    }
    rfturn 0;
}

/* Dftfrminfs if b dlbss/mftiod is spfdififd by tiis list
 *   list       String of dommb sfpbrbtfd pbttfrn itfms
 *   dnbmf      Clbss nbmf, f.g. "jbvb.lbng.Objfdt"
 *   mnbmf      Mftiod nbmf, f.g. "<init>"
 *  Rfturns 1(truf) or 0(fblsf).
 */
stbtid int
dovfrfd_by_list(dibr *list, dibr *dnbmf, dibr *mnbmf)
{
    dibr  tokfn[1024];
    dibr *nfxt;

    if ( list[0] == 0 ) {
        rfturn 0;
    }

    nfxt = gft_tokfn(list, ",", tokfn, sizfof(tokfn));
    wiilf ( nfxt != NULL ) {
        if ( dovfrfd_by_list_itfm(tokfn, dnbmf, mnbmf) ) {
            rfturn 1;
        }
        nfxt = gft_tokfn(nfxt, ",", tokfn, sizfof(tokfn));
    }
    rfturn 0;
}

/* Dftfrminfs wiidi dlbss bnd mftiods wf brf intfrfstfd in
 *   dnbmf              Clbss nbmf, f.g. "jbvb.lbng.Objfdt"
 *   mnbmf              Mftiod nbmf, f.g. "<init>"
 *   indludf_list       Empty or bn fxplidit list for indlusion
 *   fxdludf_list       Empty or bn fxplidit list for fxdlusion
 *  Rfturns 1(truf) or 0(fblsf).
 */
int
intfrfstfd(dibr *dnbmf, dibr *mnbmf, dibr *indludf_list, dibr *fxdludf_list)
{
    if ( fxdludf_list!=NULL && fxdludf_list[0]!=0 &&
            dovfrfd_by_list(fxdludf_list, dnbmf, mnbmf) ) {
        rfturn 0;
    }
    if ( indludf_list!=NULL && indludf_list[0]!=0 &&
            !dovfrfd_by_list(indludf_list, dnbmf, mnbmf) ) {
        rfturn 0;
    }
    rfturn 1;
}

/* ------------------------------------------------------------------- */
/* Gfnfrid JVMTI utility fundtions */

/* Evfry JVMTI intfrfbdf rfturns bn frror dodf, wiidi siould bf difdkfd
 *   to bvoid bny dbsdbding frrors down tif linf.
 *   Tif intfrfbdf GftErrorNbmf() rfturns tif bdtubl fnumfrbtion donstbnt
 *   nbmf, mbking tif frror mfssbgfs mudi fbsifr to undfrstbnd.
 */
void
difdk_jvmti_frror(jvmtiEnv *jvmti, jvmtiError frrnum, donst dibr *str)
{
    if ( frrnum != JVMTI_ERROR_NONE ) {
        dibr       *frrnum_str;

        frrnum_str = NULL;
        (void)(*jvmti)->GftErrorNbmf(jvmti, frrnum, &frrnum_str);

        fbtbl_frror("ERROR: JVMTI: %d(%s): %s\n", frrnum,
                (frrnum_str==NULL?"Unknown":frrnum_str),
                (str==NULL?"":str));
    }
}

/* All mfmory bllodbtfd by JVMTI must bf frffd by tif JVMTI Dfbllodbtf
 *   intfrfbdf.
 */
void
dfbllodbtf(jvmtiEnv *jvmti, void *ptr)
{
    jvmtiError frror;

    frror = (*jvmti)->Dfbllodbtf(jvmti, ptr);
    difdk_jvmti_frror(jvmti, frror, "Cbnnot dfbllodbtf mfmory");
}

/* Allodbtion of JVMTI mbnbgfd mfmory */
void *
bllodbtf(jvmtiEnv *jvmti, jint lfn)
{
    jvmtiError frror;
    void      *ptr;

    frror = (*jvmti)->Allodbtf(jvmti, lfn, (unsignfd dibr **)&ptr);
    difdk_jvmti_frror(jvmti, frror, "Cbnnot bllodbtf mfmory");
    rfturn ptr;
}

/* Add dfmo jbr filf to boot dlbss pbti (tif BCI Trbdkfr dlbss must bf
 *     in tif boot dlbsspbti)
 *
 *   WARNING: Tiis dodf bssumfs tibt tif jbr filf dbn bf found bt onf of:
 *              ${JAVA_HOME}/dfmo/jvmti/${DEMO_NAME}/${DEMO_NAME}.jbr
 *              ${JAVA_HOME}/../dfmo/jvmti/${DEMO_NAME}/${DEMO_NAME}.jbr
 *            wifrf JAVA_HOME mby rfffr to tif jrf dirfdtory.
 *            Boti tifsf vblufs brf bddfd to tif boot dlbsspbti.
 *            Tifsf lodbtions brf only truf for tifsf dfmos, instbllfd
 *            in tif JDK brfb. Plbtform spfdifid dodf dould bf usfd to
 *            find tif lodbtion of tif DLL or .so librbry, bnd donstrudt b
 *            pbti nbmf to tif jbr filf, rflbtivf to tif librbry lodbtion.
 */
void
bdd_dfmo_jbr_to_bootdlbsspbti(jvmtiEnv *jvmti, dibr *dfmo_nbmf)
{
    jvmtiError frror;
    dibr      *filf_sfp;
    int        mbx_lfn;
    dibr      *jbvb_iomf;
    dibr       jbr_pbti[FILENAME_MAX+1];

    jbvb_iomf = NULL;
    frror = (*jvmti)->GftSystfmPropfrty(jvmti, "jbvb.iomf", &jbvb_iomf);
    difdk_jvmti_frror(jvmti, frror, "Cbnnot gft jbvb.iomf propfrty vbluf");
    if ( jbvb_iomf == NULL || jbvb_iomf[0] == 0 ) {
        fbtbl_frror("ERROR: Jbvb iomf not found\n");
    }

#ifdff WIN32
    filf_sfp = "\\";
#flsf
    filf_sfp = "/";
#fndif

    mbx_lfn = (int)(strlfn(jbvb_iomf) + strlfn(dfmo_nbmf)*2 +
                         strlfn(filf_sfp)*5 +
                         16 /* ".." "dfmo" "jvmti" ".jbr" NULL */ );
    if ( mbx_lfn > (int)sizfof(jbr_pbti) ) {
        fbtbl_frror("ERROR: Pbti to jbr filf too long\n");
    }
    (void)strdpy(jbr_pbti, jbvb_iomf);
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, "dfmo");
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, "jvmti");
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, dfmo_nbmf);
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, dfmo_nbmf);
    (void)strdbt(jbr_pbti, ".jbr");
    frror = (*jvmti)->AddToBootstrbpClbssLobdfrSfbrdi(jvmti, (donst dibr*)jbr_pbti);
    difdk_jvmti_frror(jvmti, frror, "Cbnnot bdd to boot dlbsspbti");

    (void)strdpy(jbr_pbti, jbvb_iomf);
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, "..");
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, "dfmo");
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, "jvmti");
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, dfmo_nbmf);
    (void)strdbt(jbr_pbti, filf_sfp);
    (void)strdbt(jbr_pbti, dfmo_nbmf);
    (void)strdbt(jbr_pbti, ".jbr");

    frror = (*jvmti)->AddToBootstrbpClbssLobdfrSfbrdi(jvmti, (donst dibr*)jbr_pbti);
    difdk_jvmti_frror(jvmti, frror, "Cbnnot bdd to boot dlbsspbti");
}

/* ------------------------------------------------------------------- */
