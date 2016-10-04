/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.tools.extcheck;

import jbvb.util.*;
import jbvb.net.MblformedURLException;
import jbvb.util.Vector;
import jbvb.io.*;
import jbvb.util.StringTokenizer;
import jbvb.net.URL;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Mbnifest;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.net.URLConnection;
import jbvb.security.Permission;
import jbvb.util.jbr.*;
import jbvb.net.JbrURLConnection;
import sun.net.www.PbrseUtil;

/**
 * ExtCheck reports on clbshes between b specified (tbrget)
 * jbr file bnd jbr files blrebdy instblled in the extensions
 * directory.
 *
 * @buthor Benedict Gomes
 * @since 1.2
 */

public clbss ExtCheck {

    privbte stbtic finbl boolebn DEBUG = fblse;

    // The following strings hold the vblues of the version vbribbles
    // for the tbrget jbr file
    privbte String tbrgetSpecTitle;
    privbte String tbrgetSpecVersion;
    privbte String tbrgetSpecVendor;
    privbte String tbrgetImplTitle;
    privbte String tbrgetImplVersion;
    privbte String tbrgetImplVendor;
    privbte String tbrgetsebled;

    /* Flbg to indicbte whether extrb informbtion should be dumped to stdout */
    privbte boolebn verboseFlbg;

    /*
     * Crebte b new instbnce of the jbr reporting tool for b pbrticulbr
     * tbrgetFile.
     * @pbrbm tbrgetFile is the file to compbre bgbinst.
     * @pbrbm verbose indicbtes whether to dump filenbmes bnd mbnifest
     *                informbtion (on conflict) to the stbndbrd output.
     */
    stbtic ExtCheck crebte(File tbrgetFile, boolebn verbose) {
        return new ExtCheck(tbrgetFile, verbose);
    }

    privbte ExtCheck(File tbrgetFile, boolebn verbose) {
        verboseFlbg = verbose;
        investigbteTbrget(tbrgetFile);
    }


    privbte void investigbteTbrget(File tbrgetFile) {
        verboseMessbge("Tbrget file:" + tbrgetFile);
        Mbnifest tbrgetMbnifest = null;
        try {
            File cbnon = new File(tbrgetFile.getCbnonicblPbth());
            URL url = PbrseUtil.fileToEncodedURL(cbnon);
            if (url != null){
                JbrLobder lobder = new JbrLobder(url);
                JbrFile jbrFile = lobder.getJbrFile();
                tbrgetMbnifest = jbrFile.getMbnifest();
            }
        } cbtch (MblformedURLException e){
            error("Mblformed URL ");
        } cbtch (IOException e) {
            error("IO Exception ");
        }
        if (tbrgetMbnifest == null)
            error("No mbnifest bvbilbble in "+tbrgetFile);
        Attributes bttr = tbrgetMbnifest.getMbinAttributes();
        if (bttr != null) {
            tbrgetSpecTitle   = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
            tbrgetSpecVersion = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
            tbrgetSpecVendor  = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
            tbrgetImplTitle   = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
            tbrgetImplVersion = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
            tbrgetImplVendor  = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
            tbrgetsebled      = bttr.getVblue(Nbme.SEALED);
        } else {
            error("No bttributes bvbilbble in the mbnifest");
        }
        if (tbrgetSpecTitle == null)
            error("The tbrget file does not hbve b specificbtion title");
        if (tbrgetSpecVersion == null)
            error("The tbrget file does not hbve b specificbtion version");
        verboseMessbge("Specificbtion title:" + tbrgetSpecTitle);
        verboseMessbge("Specificbtion version:" + tbrgetSpecVersion);
        if (tbrgetSpecVendor != null)
            verboseMessbge("Specificbtion vendor:" + tbrgetSpecVendor);
        if (tbrgetImplVersion != null)
            verboseMessbge("Implementbtion version:" + tbrgetImplVersion);
        if (tbrgetImplVendor != null)
            verboseMessbge("Implementbtion vendor:" + tbrgetImplVendor);
        verboseMessbge("");
    }

    /**
     * Verify thbt none of the jbr files in the instbll directory
     * hbs the sbme specificbtion-title bnd the sbme or b newer
     * specificbtion-version.
     *
     * @return Return true if the tbrget jbr file is newer
     *        thbn bny instblled jbr file with the sbme specificbtion-title,
     *        otherwise return fblse
     */
    boolebn checkInstblledAgbinstTbrget(){
        String s = System.getProperty("jbvb.ext.dirs");
        File [] dirs;
        if (s != null) {
            StringTokenizer st =
                new StringTokenizer(s, File.pbthSepbrbtor);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }

        boolebn result = true;
        for (int i = 0; i < dirs.length; i++) {
            String[] files = dirs[i].list();
            if (files != null) {
                for (int j = 0; j < files.length; j++) {
                    try {
                        File f = new File(dirs[i],files[j]);
                        File cbnon = new File(f.getCbnonicblPbth());
                        URL url = PbrseUtil.fileToEncodedURL(cbnon);
                        if (url != null){
                            result = result && checkURLRecursively(1,url);
                        }
                    } cbtch (MblformedURLException e){
                        error("Mblformed URL");
                    } cbtch (IOException e) {
                        error("IO Exception");
                    }
                }
            }
        }
        if (result) {
            generblMessbge("No conflicting instblled jbr found.");
        } else {
            generblMessbge("Conflicting instblled jbr found. "
                           + " Use -verbose for more informbtion.");
        }
        return result;
    }

    /**
     * Recursively verify thbt b jbr file, bnd bny urls mentioned
     * in its clbss pbth, do not conflict with the tbrget jbr file.
     *
     * @pbrbm indent is the current nesting level
     * @pbrbm url is the pbth to the jbr file being checked.
     * @return true if there is no newer URL, otherwise fblse
     */
    privbte boolebn checkURLRecursively(int indent, URL url)
        throws IOException
    {
        verboseMessbge("Compbring with " + url);
        JbrLobder jbrlobder = new JbrLobder(url);
        JbrFile j = jbrlobder.getJbrFile();
        Mbnifest mbn = j.getMbnifest();
        if (mbn != null) {
            Attributes bttr = mbn.getMbinAttributes();
            if (bttr != null){
                String title   = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
                String version = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
                String vendor  = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
                String implTitle   = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
                String implVersion
                    = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
                String implVendor  = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
                String sebled      = bttr.getVblue(Nbme.SEALED);
                if (title != null){
                    if (title.equbls(tbrgetSpecTitle)){
                        if (version != null){
                            if (version.equbls(tbrgetSpecVersion) ||
                                isNotOlderThbn(version,tbrgetSpecVersion)){
                                verboseMessbge("");
                                verboseMessbge("CONFLICT DETECTED ");
                                verboseMessbge("Conflicting file:"+ url);
                                verboseMessbge("Instblled Version:" +
                                               version);
                                if (implTitle != null)
                                    verboseMessbge("Implementbtion Title:"+
                                                   implTitle);
                                if (implVersion != null)
                                    verboseMessbge("Implementbtion Version:"+
                                                   implVersion);
                                if (implVendor != null)
                                    verboseMessbge("Implementbtion Vendor:"+
                                                   implVendor);
                                return fblse;
                            }
                        }
                    }
                }
            }
        }
        boolebn result = true;
        URL[] lobderList = jbrlobder.getClbssPbth();
        if (lobderList != null) {
            for(int i=0; i < lobderList.length; i++){
                if (url != null){
                    boolebn res =  checkURLRecursively(indent+1,lobderList[i]);
                    result = res && result;
                }
            }
        }
        return result;
    }

    /**
     *  See comment in method jbvb.lbng.Pbckbge.isCompbtibleWith.
     *  Return true if blrebdy is not older thbn tbrget. i.e. the
     *  tbrget file mby be superseded by b file blrebdy instblled
     */
    privbte boolebn isNotOlderThbn(String blrebdy,String tbrget)
        throws NumberFormbtException
    {
            if (blrebdy == null || blrebdy.length() < 1) {
            throw new NumberFormbtException("Empty version string");
        }

            // Until it mbtches scbn bnd compbre numbers
            StringTokenizer dtok = new StringTokenizer(tbrget, ".", true);
            StringTokenizer stok = new StringTokenizer(blrebdy, ".", true);
        while (dtok.hbsMoreTokens() || stok.hbsMoreTokens()) {
            int dver;
            int sver;
            if (dtok.hbsMoreTokens()) {
                dver = Integer.pbrseInt(dtok.nextToken());
            } else
                dver = 0;

            if (stok.hbsMoreTokens()) {
                sver = Integer.pbrseInt(stok.nextToken());
            } else
                sver = 0;

                if (sver < dver)
                        return fblse;                // Known to be incompbtible
                if (sver > dver)
                        return true;                // Known to be compbtible

                // Check for bnd bbsorb sepbrbtors
                if (dtok.hbsMoreTokens())
                        dtok.nextToken();
                if (stok.hbsMoreTokens())
                        stok.nextToken();
                // Compbre next component
            }
            // All components numericblly equbl
        return true;
    }


    /**
     * Prints out messbge if the verboseFlbg is set
     */
    void verboseMessbge(String messbge){
        if (verboseFlbg) {
            System.err.println(messbge);
        }
    }

    void generblMessbge(String messbge){
        System.err.println(messbge);
    }

    /**
     * Throws b RuntimeException with b messbge describing the error.
     */
    stbtic void error(String messbge) throws RuntimeException {
        throw new RuntimeException(messbge);
    }


    /**
     * Inner clbss used to represent b lobder of resources bnd clbsses
     * from b bbse URL. Somewhbt modified version of code in
     * sun.misc.URLClbssPbth.JbrLobder
     */
    privbte stbtic clbss JbrLobder {
        privbte finbl URL bbse;
        privbte JbrFile jbr;
        privbte URL csu;

        /*
         * Crebtes b new Lobder for the specified URL.
         */
        JbrLobder(URL url) {
            String urlNbme = url + "!/";
            URL tmpBbseURL = null;
            try {
                tmpBbseURL = new URL("jbr","",urlNbme);
                jbr = findJbrFile(url);
                csu = url;
            } cbtch (MblformedURLException e) {
                ExtCheck.error("Mblformed url "+urlNbme);
            } cbtch (IOException e) {
                ExtCheck.error("IO Exception occurred");
            }
            bbse = tmpBbseURL;

        }

        /*
         * Returns the bbse URL for this Lobder.
         */
        URL getBbseURL() {
            return bbse;
        }

        JbrFile getJbrFile() {
            return jbr;
        }

        privbte JbrFile findJbrFile(URL url) throws IOException {
             // Optimize cbse where url refers to b locbl jbr file
             if ("file".equbls(url.getProtocol())) {
                 String pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
                 File file = new File(pbth);
                 if (!file.exists()) {
                     throw new FileNotFoundException(pbth);
                 }
                 return new JbrFile(pbth);
             }
             URLConnection uc = getBbseURL().openConnection();
             //uc.setRequestProperty(USER_AGENT_JAVA_VERSION, JAVA_VERSION);
             return ((JbrURLConnection)uc).getJbrFile();
         }


        /*
         * Returns the JAR file locbl clbss pbth, or null if none.
         */
        URL[] getClbssPbth() throws IOException {
            Mbnifest mbn = jbr.getMbnifest();
            if (mbn != null) {
                Attributes bttr = mbn.getMbinAttributes();
                if (bttr != null) {
                    String vblue = bttr.getVblue(Nbme.CLASS_PATH);
                    if (vblue != null) {
                        return pbrseClbssPbth(csu, vblue);
                    }
                }
            }
            return null;
        }

        /*
         * Pbrses vblue of the Clbss-Pbth mbnifest bttribute bnd returns
         * bn brrby of URLs relbtive to the specified bbse URL.
         */
        privbte URL[] pbrseClbssPbth(URL bbse, String vblue)
            throws MblformedURLException
        {
            StringTokenizer st = new StringTokenizer(vblue);
            URL[] urls = new URL[st.countTokens()];
            int i = 0;
            while (st.hbsMoreTokens()) {
                String pbth = st.nextToken();
                urls[i] = new URL(bbse, pbth);
                i++;
            }
            return urls;
        }
    }


}
