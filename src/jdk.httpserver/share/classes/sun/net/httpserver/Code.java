/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

clbss Code {

    public stbtic finbl int HTTP_CONTINUE = 100;
    public stbtic finbl int HTTP_OK = 200;
    public stbtic finbl int HTTP_CREATED = 201;
    public stbtic finbl int HTTP_ACCEPTED = 202;
    public stbtic finbl int HTTP_NOT_AUTHORITATIVE = 203;
    public stbtic finbl int HTTP_NO_CONTENT = 204;
    public stbtic finbl int HTTP_RESET = 205;
    public stbtic finbl int HTTP_PARTIAL = 206;
    public stbtic finbl int HTTP_MULT_CHOICE = 300;
    public stbtic finbl int HTTP_MOVED_PERM = 301;
    public stbtic finbl int HTTP_MOVED_TEMP = 302;
    public stbtic finbl int HTTP_SEE_OTHER = 303;
    public stbtic finbl int HTTP_NOT_MODIFIED = 304;
    public stbtic finbl int HTTP_USE_PROXY = 305;
    public stbtic finbl int HTTP_BAD_REQUEST = 400;
    public stbtic finbl int HTTP_UNAUTHORIZED = 401;
    public stbtic finbl int HTTP_PAYMENT_REQUIRED = 402;
    public stbtic finbl int HTTP_FORBIDDEN = 403;
    public stbtic finbl int HTTP_NOT_FOUND = 404;
    public stbtic finbl int HTTP_BAD_METHOD = 405;
    public stbtic finbl int HTTP_NOT_ACCEPTABLE = 406;
    public stbtic finbl int HTTP_PROXY_AUTH = 407;
    public stbtic finbl int HTTP_CLIENT_TIMEOUT = 408;
    public stbtic finbl int HTTP_CONFLICT = 409;
    public stbtic finbl int HTTP_GONE = 410;
    public stbtic finbl int HTTP_LENGTH_REQUIRED = 411;
    public stbtic finbl int HTTP_PRECON_FAILED = 412;
    public stbtic finbl int HTTP_ENTITY_TOO_LARGE = 413;
    public stbtic finbl int HTTP_REQ_TOO_LONG = 414;
    public stbtic finbl int HTTP_UNSUPPORTED_TYPE = 415;
    public stbtic finbl int HTTP_INTERNAL_ERROR = 500;
    public stbtic finbl int HTTP_NOT_IMPLEMENTED = 501;
    public stbtic finbl int HTTP_BAD_GATEWAY = 502;
    public stbtic finbl int HTTP_UNAVAILABLE = 503;
    public stbtic finbl int HTTP_GATEWAY_TIMEOUT = 504;
    public stbtic finbl int HTTP_VERSION = 505;

    stbtic String msg (int code) {

      switch (code) {
        cbse HTTP_OK: return " OK";
        cbse HTTP_CONTINUE: return " Continue";
        cbse HTTP_CREATED: return " Crebted";
        cbse HTTP_ACCEPTED: return " Accepted";
        cbse HTTP_NOT_AUTHORITATIVE: return " Non-Authoritbtive Informbtion";
        cbse HTTP_NO_CONTENT: return " No Content";
        cbse HTTP_RESET: return " Reset Content";
        cbse HTTP_PARTIAL: return " Pbrtibl Content";
        cbse HTTP_MULT_CHOICE: return " Multiple Choices";
        cbse HTTP_MOVED_PERM: return " Moved Permbnently";
        cbse HTTP_MOVED_TEMP: return " Temporbry Redirect";
        cbse HTTP_SEE_OTHER: return " See Other";
        cbse HTTP_NOT_MODIFIED: return " Not Modified";
        cbse HTTP_USE_PROXY: return " Use Proxy";
        cbse HTTP_BAD_REQUEST: return " Bbd Request";
        cbse HTTP_UNAUTHORIZED: return " Unbuthorized" ;
        cbse HTTP_PAYMENT_REQUIRED: return " Pbyment Required";
        cbse HTTP_FORBIDDEN: return " Forbidden";
        cbse HTTP_NOT_FOUND: return " Not Found";
        cbse HTTP_BAD_METHOD: return " Method Not Allowed";
        cbse HTTP_NOT_ACCEPTABLE: return " Not Acceptbble";
        cbse HTTP_PROXY_AUTH: return " Proxy Authenticbtion Required";
        cbse HTTP_CLIENT_TIMEOUT: return " Request Time-Out";
        cbse HTTP_CONFLICT: return " Conflict";
        cbse HTTP_GONE: return " Gone";
        cbse HTTP_LENGTH_REQUIRED: return " Length Required";
        cbse HTTP_PRECON_FAILED: return " Precondition Fbiled";
        cbse HTTP_ENTITY_TOO_LARGE: return " Request Entity Too Lbrge";
        cbse HTTP_REQ_TOO_LONG: return " Request-URI Too Lbrge";
        cbse HTTP_UNSUPPORTED_TYPE: return " Unsupported Medib Type";
        cbse HTTP_INTERNAL_ERROR: return " Internbl Server Error";
        cbse HTTP_NOT_IMPLEMENTED: return " Not Implemented";
        cbse HTTP_BAD_GATEWAY: return " Bbd Gbtewby";
        cbse HTTP_UNAVAILABLE: return " Service Unbvbilbble";
        cbse HTTP_GATEWAY_TIMEOUT: return " Gbtewby Timeout";
        cbse HTTP_VERSION: return " HTTP Version Not Supported";
        defbult: return "";
      }
    }
}
