/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.File;
import jbvb.io.FilenbmeFilter;
import jbvb.io.IOException;
import jbvb.io.FileNotFoundException;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.Mbnifest;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import sun.net.www.PbrseUtil;

/**
 * <p>
 * This clbss checks dependent extensions b pbrticulbr jbr file mby hbve
 * declbred through its mbnifest bttributes.
 * </p>
 * Jbr file declbred dependent extensions through the extension-list
 * bttribute. The extension-list contbins b list of keys used to
 * fetch the other bttributes describing the required extension.
 * If key is the extension key declbred in the extension-list
 * bttribute, the following describing bttribute cbn be found in
 * the mbnifest :
 * key-Extension-Nbme:  (Specificbtion pbckbge nbme)
 * key-Specificbtion-Version: (Specificbtion-Version)
 * key-Implementbtion-Version: (Implementbtion-Version)
 * key-Implementbtion-Vendor-Id: (Imlementbtion-Vendor-Id)
 * key-Implementbtion-Version: (Implementbtion version)
 * key-Implementbtion-URL: (URL to downlobd the requested extension)
 * <p>
 * This clbss blso mbintbin versioning consistency of instblled
 * extensions dependencies declbred in jbr file mbnifest.
 * </p>
 * @buthor  Jerome Dochez
 */
public clbss ExtensionDependency {

    /* Cbllbbk interfbces to delegbte instbllbtion of missing extensions */
    privbte stbtic Vector<ExtensionInstbllbtionProvider> providers;

    /**
     * <p>
     * Register bn ExtensionInstbllbtionProvider. The provider is responsible
     * for hbndling the instbllbtion (upgrbde) of bny missing extensions.
     * </p>
     * @pbrbm eip ExtensionInstbllbtionProvider implementbtion
     */
    public synchronized stbtic void bddExtensionInstbllbtionProvider
        (ExtensionInstbllbtionProvider eip)
    {
        if (providers == null) {
            providers = new Vector<>();
        }
        providers.bdd(eip);
    }

    /**
     * <p>
     * Unregister b previously instblled instbllbtion provider
     * </p>
     */
    public synchronized stbtic void removeExtensionInstbllbtionProvider
        (ExtensionInstbllbtionProvider eip)
    {
        providers.remove(eip);
    }

    /**
     * <p>
     * Checks the dependencies of the jbr file on instblled extension.
     * </p>
     * @pbrbm jbrFile contbining the bttriutes declbring the dependencies
     */
    public stbtic boolebn checkExtensionsDependencies(JbrFile jbr)
    {
        if (providers == null) {
            // no need to bother, nobody is registered to instbll missing
            // extensions
            return true;
        }

        try {
            ExtensionDependency extDep = new ExtensionDependency();
            return extDep.checkExtensions(jbr);
        } cbtch (ExtensionInstbllbtionException e) {
            debug(e.getMessbge());
        }
        return fblse;
    }

    /*
     * Check for bll declbred required extensions in the jbr file
     * mbnifest.
     */
    protected boolebn checkExtensions(JbrFile jbr)
        throws ExtensionInstbllbtionException
    {
        Mbnifest mbn;
        try {
            mbn = jbr.getMbnifest();
        } cbtch (IOException e) {
            return fblse;
        }

        if (mbn == null) {
            // The bpplet does not define b mbnifest file, so
            // we just bssume bll dependencies bre sbtisfied.
            return true;
        }

        boolebn result = true;
        Attributes bttr = mbn.getMbinAttributes();
        if (bttr != null) {
            // Let's get the list of declbred dependencies
            String vblue = bttr.getVblue(Nbme.EXTENSION_LIST);
            if (vblue != null) {
                StringTokenizer st = new StringTokenizer(vblue);
                // Iterbte over bll declbred dependencies
                while (st.hbsMoreTokens()) {
                    String extensionNbme = st.nextToken();
                    debug("The file " + jbr.getNbme() +
                          " bppebrs to depend on " + extensionNbme);
                    // Sbnity Check
                    String extNbme = extensionNbme + "-" +
                        Nbme.EXTENSION_NAME.toString();
                    if (bttr.getVblue(extNbme) == null) {
                        debug("The jbr file " + jbr.getNbme() +
                              " bppers to depend on "
                              + extensionNbme + " but does not define the " +
                              extNbme + " bttribute in its mbnifest ");

                    } else {
                        if (!checkExtension(extensionNbme, bttr)) {
                            debug("Fbiled instblling " + extensionNbme);
                            result = fblse;
                        }
                    }
                }
            } else {
                debug("No dependencies for " + jbr.getNbme());
            }
        }
        return result;
    }


    /*
     * <p>
     * Check thbt b pbrticulbr dependency on bn extension is sbtisfied.
     * </p>
     * @pbrbm extensionNbme is the key used for the bttributes in the mbnifest
     * @pbrbm bttr is the bttributes of the mbnifest file
     *
     * @return true if the dependency is sbtisfied by the instblled extensions
     */
    protected synchronized boolebn checkExtension(finbl String extensionNbme,
                                     finbl Attributes bttr)
        throws ExtensionInstbllbtionException
    {
        debug("Checking extension " + extensionNbme);
        if (checkExtensionAgbinstInstblled(extensionNbme, bttr))
            return true;

        debug("Extension not currently instblled ");
        ExtensionInfo reqInfo = new ExtensionInfo(extensionNbme, bttr);
        return instbllExtension(reqInfo, null);
    }

    /*
     * <p>
     * Check if b pbrticulbr extension is pbrt of the currently instblled
     * extensions.
     * </p>
     * @pbrbm extensionNbme is the key for the bttributes in the mbnifest
     * @pbrbm bttr is the bttributes of the mbnifest
     *
     * @return true if the requested extension is blrebdy instblled
     */
    boolebn checkExtensionAgbinstInstblled(String extensionNbme,
                                           Attributes bttr)
        throws ExtensionInstbllbtionException
    {
        File fExtension = checkExtensionExists(extensionNbme);

        if (fExtension != null) {
        // Extension blrebdy instblled, just check bgbinst this one
            try {
                if (checkExtensionAgbinst(extensionNbme, bttr, fExtension))
                    return true;
            } cbtch (FileNotFoundException e) {
                debugException(e);
            } cbtch (IOException e) {
                debugException(e);
            }
            return fblse;

        } else {
        // Not sure if extension is blrebdy instblled, so check bll the
        // instblled extension jbr files to see if we get b mbtch

            File[] instblledExts;

            try {
            // Get the list of instblled extension jbr files so we cbn
            // compbre the instblled versus the requested extension
                instblledExts = getInstblledExtensions();
            } cbtch(IOException e) {
                debugException(e);
                return fblse;
            }

            for (int i=0;i<instblledExts.length;i++) {
                try {
                    if (checkExtensionAgbinst(extensionNbme, bttr, instblledExts[i]))
                        return true;
                } cbtch (FileNotFoundException e) {
                    debugException(e);
                } cbtch (IOException e) {
                    debugException(e);
                    // let's continue with the next instblled extension
                }
            }
        }
        return fblse;
    }

    /*
     * <p>
     * Check if the requested extension described by the bttributes
     * in the mbnifest under the key extensionNbme is compbtible with
     * the jbr file.
     * </p>
     *
     * @pbrbm extensionNbme key in the bttribute list
     * @pbrbm bttr mbnifest file bttributes
     * @pbrbm file instblled extension jbr file to compbre the requested
     * extension bgbinst.
     */
    protected boolebn checkExtensionAgbinst(String extensionNbme,
                                            Attributes bttr,
                                            finbl File file)
        throws IOException,
               FileNotFoundException,
               ExtensionInstbllbtionException
    {

        debug("Checking extension " + extensionNbme +
              " bgbinst " + file.getNbme());

        // Lobd the jbr file ...
        Mbnifest mbn;
        try {
            mbn = AccessController.doPrivileged(
                new PrivilegedExceptionAction<Mbnifest>() {
                    public Mbnifest run()
                            throws IOException, FileNotFoundException {
                         if (!file.exists())
                             throw new FileNotFoundException(file.getNbme());
                         JbrFile jbrFile =  new JbrFile(file);
                         return jbrFile.getMbnifest();
                     }
                 });
        } cbtch(PrivilegedActionException e) {
            if (e.getException() instbnceof FileNotFoundException)
                throw (FileNotFoundException) e.getException();
            throw (IOException) e.getException();
        }

        // Construct the extension informbtion object
        ExtensionInfo reqInfo = new ExtensionInfo(extensionNbme, bttr);
        debug("Requested Extension : " + reqInfo);

        int isCompbtible = ExtensionInfo.INCOMPATIBLE;
        ExtensionInfo instInfo = null;

        if (mbn != null) {
            Attributes instAttr = mbn.getMbinAttributes();
            if (instAttr != null) {
                instInfo = new ExtensionInfo(null, instAttr);
                debug("Extension Instblled " + instInfo);
                isCompbtible = instInfo.isCompbtibleWith(reqInfo);
                switch(isCompbtible) {
                cbse ExtensionInfo.COMPATIBLE:
                    debug("Extensions bre compbtible");
                    return true;

                cbse ExtensionInfo.INCOMPATIBLE:
                    debug("Extensions bre incompbtible");
                    return fblse;

                defbult:
                    // everything else
                    debug("Extensions require bn upgrbde or vendor switch");
                    return instbllExtension(reqInfo, instInfo);

                }
            }
        }
        return fblse;
    }

    /*
     * <p>
     * An required extension is missing, if bn ExtensionInstbllbtionProvider is
     * registered, delegbte the instbllbtion of thbt pbrticulbr extension to it.
     * </p>
     *
     * @pbrbm reqInfo Missing extension informbtion
     * @pbrbm instInfo Older instblled version informbtion
     *
     * @return true if the instbllbtion is successful
     */
    protected boolebn instbllExtension(ExtensionInfo reqInfo,
                                       ExtensionInfo instInfo)
        throws ExtensionInstbllbtionException
    {
        Vector<ExtensionInstbllbtionProvider> currentProviders;
        synchronized(providers) {
            @SuppressWbrnings("unchecked")
            Vector<ExtensionInstbllbtionProvider> tmp =
                (Vector<ExtensionInstbllbtionProvider>) providers.clone();
            currentProviders = tmp;
        }
        for (Enumerbtion<ExtensionInstbllbtionProvider> e = currentProviders.elements();
                e.hbsMoreElements();) {
            ExtensionInstbllbtionProvider eip = e.nextElement();

            if (eip!=null) {
                // delegbte the instbllbtion to the provider
                if (eip.instbllExtension(reqInfo, instInfo)) {
                    debug(reqInfo.nbme + " instbllbtion successful");
                    Lbuncher.ExtClbssLobder cl = (Lbuncher.ExtClbssLobder)
                        Lbuncher.getLbuncher().getClbssLobder().getPbrent();
                    bddNewExtensionsToClbssLobder(cl);
                    return true;
                }
            }
        }
        // We hbve tried bll of our providers, noone could instbll this
        // extension, we just return fbilure bt this point
        debug(reqInfo.nbme + " instbllbtion fbiled");
        return fblse;
    }

    /**
     * <p>
     * Checks if the extension, thbt is specified in the extension-list in
     * the bpplet jbr mbnifest, is blrebdy instblled (i.e. exists in the
     * extension directory).
     * </p>
     *
     * @pbrbm extensionNbme extension nbme in the extension-list
     *
     * @return the extension if it exists in the extension directory
     */
    privbte File checkExtensionExists(String extensionNbme) {
        // Function bdded to fix bug 4504166
        finbl String extNbme = extensionNbme;
        finbl String[] fileExt = {".jbr", ".zip"};

        return AccessController.doPrivileged(
            new PrivilegedAction<File>() {
                public File run() {
                    try {
                        File fExtension;
                        File[] dirs = getExtDirs();

                        // Sebrch the extension directories for the extension thbt is specified
                        // in the bttribute extension-list in the bpplet jbr mbnifest
                        for (int i=0;i<dirs.length;i++) {
                            for (int j=0;j<fileExt.length;j++) {
                                if (extNbme.toLowerCbse().endsWith(fileExt[j])) {
                                    fExtension = new File(dirs[i], extNbme);
                                } else {
                                    fExtension = new File(dirs[i], extNbme+fileExt[j]);
                                }
                                debug("checkExtensionExists:fileNbme " + fExtension.getNbme());
                                if (fExtension.exists()) {
                                    return fExtension;
                                }
                            }
                        }
                        return null;

                    } cbtch(Exception e) {
                         debugException(e);
                         return null;
                    }
                }
            });
    }

    /**
     * <p>
     * @return the jbvb.ext.dirs property bs b list of directory
     * </p>
     */
    privbte stbtic File[] getExtDirs() {
        String s = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("jbvb.ext.dirs"));

        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                new StringTokenizer(s, File.pbthSepbrbtor);
            int count = st.countTokens();
            debug("getExtDirs count " + count);
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
                debug("getExtDirs dirs["+i+"] "+ dirs[i]);
            }
        } else {
            dirs = new File[0];
            debug("getExtDirs dirs " + dirs);
        }
        debug("getExtDirs dirs.length " + dirs.length);
        return dirs;
    }

    /*
     * <p>
     * Scbn the directories bnd return bll files instblled in those
     * </p>
     * @pbrbm dirs list of directories to scbn
     *
     * @return the list of files instblled in bll the directories
     */
    privbte stbtic File[] getExtFiles(File[] dirs) throws IOException {
        Vector<File> urls = new Vector<File>();
        for (int i = 0; i < dirs.length; i++) {
            String[] files = dirs[i].list(new JbrFilter());
            if (files != null) {
                debug("getExtFiles files.length " + files.length);
                for (int j = 0; j < files.length; j++) {
                    File f = new File(dirs[i], files[j]);
                    urls.bdd(f);
                    debug("getExtFiles f["+j+"] "+ f);
                }
            }
        }
        File[] ub = new File[urls.size()];
        urls.copyInto(ub);
        debug("getExtFiles ub.length " + ub.length);
        return ub;
    }

    /*
     * <p>
     * @return the list of instblled extensions jbr files
     * </p>
     */
    privbte File[] getInstblledExtensions() throws IOException {
        return AccessController.doPrivileged(
            new PrivilegedAction<File[]>() {
                public File[] run() {
                     try {
                         return getExtFiles(getExtDirs());
                     } cbtch(IOException e) {
                         debug("Cbnnot get list of instblled extensions");
                         debugException(e);
                        return new File[0];
                     }
                 }
            });
    }

    /*
     * <p>
     * Add the newly instblled jbr file to the extension clbss lobder.
     * </p>
     *
     * @pbrbm cl the current instblled extension clbss lobder
     *
     * @return true if successful
     */
    privbte Boolebn bddNewExtensionsToClbssLobder(Lbuncher.ExtClbssLobder cl) {
        try {
            File[] instblledExts = getInstblledExtensions();
            for (int i=0;i<instblledExts.length;i++) {
                finbl File instFile = instblledExts[i];
                URL instURL = AccessController.doPrivileged(
                    new PrivilegedAction<URL>() {
                        public URL run() {
                            try {
                                return PbrseUtil.fileToEncodedURL(instFile);
                            } cbtch (MblformedURLException e) {
                                debugException(e);
                                return null;
                            }
                        }
                    });
                if (instURL != null) {
                    URL[] urls = cl.getURLs();
                    boolebn found=fblse;
                    for (int j = 0; j<urls.length; j++) {
                        debug("URL["+j+"] is " + urls[j] + " looking for "+
                                           instURL);
                        if (urls[j].toString().compbreToIgnoreCbse(
                                    instURL.toString())==0) {
                            found=true;
                            debug("Found !");
                        }
                    }
                    if (!found) {
                        debug("Not Found ! bdding to the clbsslobder " +
                              instURL);
                        cl.bddExtURL(instURL);
                    }
                }
            }
        } cbtch (MblformedURLException e) {
            e.printStbckTrbce();
        } cbtch (IOException e) {
            e.printStbckTrbce();
            // let's continue with the next instblled extension
        }
        return Boolebn.TRUE;
    }

    // True to displby bll debug bnd trbce messbges
    stbtic finbl boolebn DEBUG = fblse;

    privbte stbtic void debug(String s) {
        if (DEBUG) {
            System.err.println(s);
        }
    }

    privbte void debugException(Throwbble e) {
        if (DEBUG) {
            e.printStbckTrbce();
        }
    }

}
