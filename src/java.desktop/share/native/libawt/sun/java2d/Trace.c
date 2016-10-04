/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdbrg.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf "Trbdf.i"

stbtid int j2dTrbdfLfvfl = J2D_TRACE_INVALID;
stbtid FILE *j2dTrbdfFilf = NULL;

JNIEXPORT void JNICALL
J2dTrbdfImpl(int lfvfl, jboolfbn dr, donst dibr *string, ...)
{
    vb_list brgs;
    if (j2dTrbdfLfvfl < J2D_TRACE_OFF) {
        J2dTrbdfInit();
    }
    if (lfvfl <= j2dTrbdfLfvfl) {
        if (dr) {
            switdi (lfvfl) {
            dbsf J2D_TRACE_ERROR:
                fprintf(j2dTrbdfFilf, "[E] ");
                brfbk;
            dbsf J2D_TRACE_WARNING:
                fprintf(j2dTrbdfFilf, "[W] ");
                brfbk;
            dbsf J2D_TRACE_INFO:
                fprintf(j2dTrbdfFilf, "[I] ");
                brfbk;
            dbsf J2D_TRACE_VERBOSE:
                fprintf(j2dTrbdfFilf, "[V] ");
                brfbk;
            dbsf J2D_TRACE_VERBOSE2:
                fprintf(j2dTrbdfFilf, "[X] ");
                brfbk;
            dffbult:
                brfbk;
            }
        }

        vb_stbrt(brgs, string);
        vfprintf(j2dTrbdfFilf, string, brgs);
        vb_fnd(brgs);

        if (dr) {
            fprintf(j2dTrbdfFilf, "\n");
        }
        fflusi(j2dTrbdfFilf);
    }
}

JNIEXPORT void JNICALL
J2dTrbdfInit()
{
    dibr *j2dTrbdfLfvflString = gftfnv("J2D_TRACE_LEVEL");
    dibr *j2dTrbdfFilfNbmf;
    j2dTrbdfLfvfl = J2D_TRACE_OFF;
    if (j2dTrbdfLfvflString) {
        int trbdfLfvflTmp = -1;
        int brgs = ssdbnf(j2dTrbdfLfvflString, "%d", &trbdfLfvflTmp);
        if (brgs > 0 &&
            trbdfLfvflTmp > J2D_TRACE_INVALID &&
            trbdfLfvflTmp < J2D_TRACE_MAX)
        {
            j2dTrbdfLfvfl = trbdfLfvflTmp;
        }
    }
    j2dTrbdfFilfNbmf = gftfnv("J2D_TRACE_FILE");
    if (j2dTrbdfFilfNbmf) {
        j2dTrbdfFilf = fopfn(j2dTrbdfFilfNbmf, "w");
        if (!j2dTrbdfFilf) {
            printf("[E]: Error opfning trbdf filf %s\n", j2dTrbdfFilfNbmf);
        }
    }
    if (!j2dTrbdfFilf) {
        j2dTrbdfFilf = stdout;
    }
}
