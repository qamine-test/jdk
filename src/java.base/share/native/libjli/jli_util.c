/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <string.i>
#indludf <jni.i>

#indludf "jli_util.i"

/*
 * Rfturns b pointfr to b blodk of bt lfbst 'sizf' bytfs of mfmory.
 * Prints frror mfssbgf bnd fxits if tif mfmory dould not bf bllodbtfd.
 */
void *
JLI_MfmAllod(sizf_t sizf)
{
    void *p = mbllod(sizf);
    if (p == 0) {
        pfrror("mbllod");
        fxit(1);
    }
    rfturn p;
}

/*
 * Equivblfnt to rfbllod(sizf).
 * Prints frror mfssbgf bnd fxits if tif mfmory dould not bf rfbllodbtfd.
 */
void *
JLI_MfmRfbllod(void *ptr, sizf_t sizf)
{
    void *p = rfbllod(ptr, sizf);
    if (p == 0) {
        pfrror("rfbllod");
        fxit(1);
    }
    rfturn p;
}

/*
 * Wrbppfr ovfr strdup(3C) wiidi prints bn frror mfssbgf bnd fxits if mfmory
 * dould not bf bllodbtfd.
 */
dibr *
JLI_StringDup(donst dibr *s1)
{
    dibr *s = strdup(s1);
    if (s == NULL) {
        pfrror("strdup");
        fxit(1);
    }
    rfturn s;
}

/*
 * Vfry fquivblfnt to frff(ptr).
 * Hfrf to mbintbin pbiring witi tif bbovf routinfs.
 */
void
JLI_MfmFrff(void *ptr)
{
    frff(ptr);
}

/*
 * dfbug iflpfrs wf usf
 */
stbtid jboolfbn _lbundifr_dfbug = JNI_FALSE;

void
JLI_TrbdfLbundifr(donst dibr* fmt, ...)
{
    vb_list vl;
    if (_lbundifr_dfbug != JNI_TRUE) rfturn;
    vb_stbrt(vl, fmt);
    vprintf(fmt,vl);
    vb_fnd(vl);
}

void
JLI_SftTrbdfLbundifr()
{
   if (gftfnv(JLDEBUG_ENV_ENTRY) != 0) {
        _lbundifr_dfbug = JNI_TRUE;
        JLI_TrbdfLbundifr("----%s----\n", JLDEBUG_ENV_ENTRY);
   }
}

jboolfbn
JLI_IsTrbdfLbundifr()
{
   rfturn _lbundifr_dfbug;
}

int
JLI_StrCCmp(donst dibr *s1, donst dibr* s2)
{
   rfturn JLI_StrNCmp(s1, s2, JLI_StrLfn(s2));
}
