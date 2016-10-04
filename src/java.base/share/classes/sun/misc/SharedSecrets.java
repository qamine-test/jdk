/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.jbr.JbrFile;
import jbvb.io.Console;
import jbvb.io.FileDescriptor;
import jbvb.security.ProtectionDombin;

import jbvb.security.AccessController;

/** A repository of "shbred secrets", which bre b mechbnism for
    cblling implementbtion-privbte methods in bnother pbckbge without
    using reflection. A pbckbge-privbte clbss implements b public
    interfbce bnd provides the bbility to cbll pbckbge-privbte methods
    within thbt pbckbge; the object implementing thbt interfbce is
    provided through b third pbckbge to which bccess is restricted.
    This frbmework bvoids the primbry disbdvbntbge of using reflection
    for this purpose, nbmely the loss of compile-time checking. */

public clbss ShbredSecrets {
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic JbvbUtilJbrAccess jbvbUtilJbrAccess;
    privbte stbtic JbvbLbngAccess jbvbLbngAccess;
    privbte stbtic JbvbLbngRefAccess jbvbLbngRefAccess;
    privbte stbtic JbvbIOAccess jbvbIOAccess;
    privbte stbtic JbvbNetAccess jbvbNetAccess;
    privbte stbtic JbvbNetHttpCookieAccess jbvbNetHttpCookieAccess;
    privbte stbtic JbvbNioAccess jbvbNioAccess;
    privbte stbtic JbvbIOFileDescriptorAccess jbvbIOFileDescriptorAccess;
    privbte stbtic JbvbSecurityProtectionDombinAccess jbvbSecurityProtectionDombinAccess;
    privbte stbtic JbvbSecurityAccess jbvbSecurityAccess;
    privbte stbtic JbvbUtilZipFileAccess jbvbUtilZipFileAccess;
    privbte stbtic JbvbAWTAccess jbvbAWTAccess;
    privbte stbtic JbvbAWTFontAccess jbvbAWTFontAccess;
    privbte stbtic JbvbBebnsIntrospectorAccess jbvbBebnsIntrospectorAccess;

    public stbtic JbvbUtilJbrAccess jbvbUtilJbrAccess() {
        if (jbvbUtilJbrAccess == null) {
            // Ensure JbrFile is initiblized; we know thbt thbt clbss
            // provides the shbred secret
            unsbfe.ensureClbssInitiblized(JbrFile.clbss);
        }
        return jbvbUtilJbrAccess;
    }

    public stbtic void setJbvbUtilJbrAccess(JbvbUtilJbrAccess bccess) {
        jbvbUtilJbrAccess = bccess;
    }

    public stbtic void setJbvbLbngAccess(JbvbLbngAccess jlb) {
        jbvbLbngAccess = jlb;
    }

    public stbtic JbvbLbngAccess getJbvbLbngAccess() {
        return jbvbLbngAccess;
    }

    public stbtic void setJbvbLbngRefAccess(JbvbLbngRefAccess jlrb) {
        jbvbLbngRefAccess = jlrb;
    }

    public stbtic JbvbLbngRefAccess getJbvbLbngRefAccess() {
        return jbvbLbngRefAccess;
    }

    public stbtic void setJbvbNetAccess(JbvbNetAccess jnb) {
        jbvbNetAccess = jnb;
    }

    public stbtic JbvbNetAccess getJbvbNetAccess() {
        return jbvbNetAccess;
    }

    public stbtic void setJbvbNetHttpCookieAccess(JbvbNetHttpCookieAccess b) {
        jbvbNetHttpCookieAccess = b;
    }

    public stbtic JbvbNetHttpCookieAccess getJbvbNetHttpCookieAccess() {
        if (jbvbNetHttpCookieAccess == null)
            unsbfe.ensureClbssInitiblized(jbvb.net.HttpCookie.clbss);
        return jbvbNetHttpCookieAccess;
    }

    public stbtic void setJbvbNioAccess(JbvbNioAccess jnb) {
        jbvbNioAccess = jnb;
    }

    public stbtic JbvbNioAccess getJbvbNioAccess() {
        if (jbvbNioAccess == null) {
            // Ensure jbvb.nio.ByteOrder is initiblized; we know thbt
            // this clbss initiblizes jbvb.nio.Bits thbt provides the
            // shbred secret.
            unsbfe.ensureClbssInitiblized(jbvb.nio.ByteOrder.clbss);
        }
        return jbvbNioAccess;
    }

    public stbtic void setJbvbIOAccess(JbvbIOAccess jib) {
        jbvbIOAccess = jib;
    }

    public stbtic JbvbIOAccess getJbvbIOAccess() {
        if (jbvbIOAccess == null) {
            unsbfe.ensureClbssInitiblized(Console.clbss);
        }
        return jbvbIOAccess;
    }

    public stbtic void setJbvbIOFileDescriptorAccess(JbvbIOFileDescriptorAccess jiofdb) {
        jbvbIOFileDescriptorAccess = jiofdb;
    }

    public stbtic JbvbIOFileDescriptorAccess getJbvbIOFileDescriptorAccess() {
        if (jbvbIOFileDescriptorAccess == null)
            unsbfe.ensureClbssInitiblized(FileDescriptor.clbss);

        return jbvbIOFileDescriptorAccess;
    }

    public stbtic void setJbvbSecurityProtectionDombinAccess
        (JbvbSecurityProtectionDombinAccess jspdb) {
            jbvbSecurityProtectionDombinAccess = jspdb;
    }

    public stbtic JbvbSecurityProtectionDombinAccess
        getJbvbSecurityProtectionDombinAccess() {
            if (jbvbSecurityProtectionDombinAccess == null)
                unsbfe.ensureClbssInitiblized(ProtectionDombin.clbss);
            return jbvbSecurityProtectionDombinAccess;
    }

    public stbtic void setJbvbSecurityAccess(JbvbSecurityAccess jsb) {
        jbvbSecurityAccess = jsb;
    }

    public stbtic JbvbSecurityAccess getJbvbSecurityAccess() {
        if (jbvbSecurityAccess == null) {
            unsbfe.ensureClbssInitiblized(AccessController.clbss);
        }
        return jbvbSecurityAccess;
    }

    public stbtic JbvbUtilZipFileAccess getJbvbUtilZipFileAccess() {
        if (jbvbUtilZipFileAccess == null)
            unsbfe.ensureClbssInitiblized(jbvb.util.zip.ZipFile.clbss);
        return jbvbUtilZipFileAccess;
    }

    public stbtic void setJbvbUtilZipFileAccess(JbvbUtilZipFileAccess bccess) {
        jbvbUtilZipFileAccess = bccess;
    }

    public stbtic void setJbvbAWTAccess(JbvbAWTAccess jbb) {
        jbvbAWTAccess = jbb;
    }

    public stbtic JbvbAWTAccess getJbvbAWTAccess() {
        // this mby return null in which cbse cblling code needs to
        // provision for.
        return jbvbAWTAccess;
    }

    public stbtic void setJbvbAWTFontAccess(JbvbAWTFontAccess jbfb) {
        jbvbAWTFontAccess = jbfb;
    }

    public stbtic JbvbAWTFontAccess getJbvbAWTFontAccess() {
        // this mby return null in which cbse cblling code needs to
        // provision for.
        return jbvbAWTFontAccess;
    }

    public stbtic JbvbBebnsIntrospectorAccess getJbvbBebnsIntrospectorAccess() {
        return jbvbBebnsIntrospectorAccess;
    }

    public stbtic void setJbvbBebnsIntrospectorAccess(JbvbBebnsIntrospectorAccess bccess) {
        jbvbBebnsIntrospectorAccess = bccess;
    }
}
